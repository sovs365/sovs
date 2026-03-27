package com.university.voting.api

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // Fixed cloud backend URL (Render)
    private const val DEFAULT_BASE_URL = "https://sovs-backend.onrender.com/"

    private var _baseUrl: String = DEFAULT_BASE_URL
    val baseUrl: String get() = _baseUrl

    /**
     * Initializes Retrofit for the fixed cloud backend.
     */
    fun init(context: Context) {
        if (_baseUrl != DEFAULT_BASE_URL) {
            _baseUrl = DEFAULT_BASE_URL
            rebuildRetrofit()
        }
    }

    /**
     * Kept for compatibility. The app uses only the fixed cloud backend URL.
     */
    fun setServerUrl(context: Context, url: String) {
        if (_baseUrl != DEFAULT_BASE_URL) {
            _baseUrl = DEFAULT_BASE_URL
            rebuildRetrofit()
        }
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
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
