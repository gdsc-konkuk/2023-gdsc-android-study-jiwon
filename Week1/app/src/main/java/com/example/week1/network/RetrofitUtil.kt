package com.example.week1.network

import de.hdodenhof.circleimageview.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtil {

    private const val BASE_URL = "https://api.unsplash.com"
    private var instance: Retrofit? = null
    val unsplashService: UnsplashService by lazy {
        getRetrofit().create(UnsplashService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildOkHttpClient())
                .build()
        }

        return requireNotNull(instance) { "RetrofitUtil's instance is null" }
    }

    private fun buildOkHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()

        /*val baseParameterInterceptor: Interceptor = (object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val originalRequest = chain.request()

                val addQuery = originalRequest.url.newBuilder().addQueryParameter(CLIENT_ID, SECRET_KEY).build()

                val finalRequest = originalRequest.newBuilder()
                    .url(addQuery)
                    .method(originalRequest.method, originalRequest.body)
                    .build()

                return chain.proceed(finalRequest)
            }

        })*/

        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }
}