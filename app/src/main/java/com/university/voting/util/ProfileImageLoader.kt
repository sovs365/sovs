package com.university.voting.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.widget.ImageView
import com.university.voting.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.net.URL
import java.util.concurrent.ConcurrentHashMap

object ProfileImageLoader {
    private val bitmapCache = ConcurrentHashMap<String, Bitmap>()

    fun loadInto(
        imageView: ImageView,
        profilePhotoPath: String?,
        placeholderRes: Int = R.drawable.ic_person
    ) {
        val normalizedPath = profilePhotoPath?.trim().orEmpty()
        if (normalizedPath.isBlank()) {
            imageView.setImageResource(placeholderRes)
            imageView.tag = null
            return
        }

        imageView.tag = normalizedPath
        imageView.setImageResource(placeholderRes)

        val cached = bitmapCache[normalizedPath]
        if (cached != null) {
            imageView.setImageBitmap(cached)
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            val bitmap = decodeBitmap(imageView.context, normalizedPath)
            withContext(Dispatchers.Main) {
                if (imageView.tag != normalizedPath) return@withContext
                if (bitmap != null) {
                    bitmapCache[normalizedPath] = bitmap
                    imageView.setImageBitmap(bitmap)
                } else {
                    imageView.setImageResource(placeholderRes)
                }
            }
        }
    }

    fun toDataUri(context: Context, uri: Uri): String? {
        return try {
            context.contentResolver.openInputStream(uri)?.use { stream ->
                val bitmap = BitmapFactory.decodeStream(stream) ?: return null
                val scaled = scaleBitmap(bitmap, maxDimension = 512)
                val output = ByteArrayOutputStream()
                scaled.compress(Bitmap.CompressFormat.JPEG, 78, output)
                val encoded = Base64.encodeToString(output.toByteArray(), Base64.NO_WRAP)
                "data:image/jpeg;base64,$encoded"
            }
        } catch (_: Exception) {
            null
        }
    }

    private fun decodeBitmap(context: Context, path: String): Bitmap? {
        return try {
            when {
                path.startsWith("data:image", ignoreCase = true) -> {
                    decodeDataUri(path)
                }

                path.startsWith("content://", ignoreCase = true) ||
                    path.startsWith("file://", ignoreCase = true) -> {
                    context.contentResolver.openInputStream(Uri.parse(path))?.use { stream ->
                        BitmapFactory.decodeStream(stream)
                    }
                }

                path.startsWith("http://", ignoreCase = true) ||
                    path.startsWith("https://", ignoreCase = true) -> {
                    val connection = URL(path).openConnection().apply {
                        connectTimeout = 8000
                        readTimeout = 8000
                    }
                    connection.getInputStream().use { stream ->
                        BitmapFactory.decodeStream(stream)
                    }
                }

                else -> null
            }
        } catch (_: Exception) {
            null
        }
    }

    private fun decodeDataUri(dataUri: String): Bitmap? {
        return try {
            val base64Part = dataUri.substringAfter(',', missingDelimiterValue = "")
            if (base64Part.isBlank()) return null
            val bytes = Base64.decode(base64Part, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        } catch (_: Exception) {
            null
        }
    }

    private fun scaleBitmap(bitmap: Bitmap, maxDimension: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        if (width <= maxDimension && height <= maxDimension) return bitmap

        val ratio = if (width > height) {
            maxDimension.toFloat() / width.toFloat()
        } else {
            maxDimension.toFloat() / height.toFloat()
        }

        val targetWidth = (width * ratio).toInt().coerceAtLeast(1)
        val targetHeight = (height * ratio).toInt().coerceAtLeast(1)
        return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true)
    }
}
