package com.app.android.data.source.remote.core

import com.app.android.BuildConfig
import com.app.android.pref.Pref
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * API access management class
 */
object ApiClient {

    private const val TIMEOUT_READ = 20000
    private const val TIMEOUT_WRITE = 20000
    private const val HEADER_AUTH = "X-Authorization"
    private const val AUTH_PREFIX = "Bearer "

    val API: Api by lazy {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder()

            val accessToken = Pref.accessToken

            if (accessToken.isNotBlank()) {
                // Pass access token to request header
                request.addHeader(HEADER_AUTH, AUTH_PREFIX + accessToken)
            }
            chain.proceed(request.build())
        }

        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        httpClient.readTimeout(TIMEOUT_READ.toLong(), TimeUnit.MILLISECONDS)
        httpClient.writeTimeout(TIMEOUT_WRITE.toLong(), TimeUnit.MILLISECONDS)

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()))
                .client(httpClient.build())
                .build()

        retrofit.create(Api::class.java)
    }
}
