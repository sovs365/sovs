package com.university.voting.api

import android.content.Context
import com.university.voting.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // Fixed cloud backend URL (Render) shared across emulator + APK installs.
    private val DEFAULT_BASE_URL = normalizeBaseUrl(BuildConfig.API_BASE_URL)

    private var _baseUrl: String = DEFAULT_BASE_URL
    val baseUrl: String get() = _baseUrl

    /**
     * Initializes Retrofit for the fixed cloud backend.
     */
    fun init(context: Context) {
        enforceDefaultBaseUrl()
    }

    /**
     * Kept for compatibility. The app uses only the fixed cloud backend URL.
     */
    fun setServerUrl(context: Context, url: String) {
        enforceDefaultBaseUrl()
    }

    private fun enforceDefaultBaseUrl() {
        if (_baseUrl != DEFAULT_BASE_URL) {
            _baseUrl = DEFAULT_BASE_URL
            rebuildRetrofit()
        }
    }

    private fun normalizeBaseUrl(rawUrl: String): String {
        val trimmed = rawUrl.trim()
        if (trimmed.isBlank()) return "https://sovs-zeo8.onrender.com/"

        val withScheme = if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
            trimmed
        } else {
            "https://$trimmed"
        }

        return if (withScheme.endsWith("/")) withScheme else "$withScheme/"
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .callTimeout(60, TimeUnit.SECONDS)
        .build()

    private var retrofit: Retrofit = buildRetrofit()

    private fun buildRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(_baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun rebuildRetrofit() {
        retrofit = buildRetrofit()
        _apiService = retrofit.create(ApiService::class.java)
    }

    private var _apiService: ApiService = retrofit.create(ApiService::class.java)
    val apiService: ApiService get() = _apiService

    fun getAuthHeader(token: String): String = "Bearer $token"
}
