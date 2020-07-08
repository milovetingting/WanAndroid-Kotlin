package com.wangyz.wanandroid.kotlin.net

import com.wangyz.wanandroid.kotlin.Config
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {

    companion object {
        val INSTANCE = APIClient()
    }

    fun <T> retrofit(clazz: Class<T>): T {
        val okHttpClient = OkHttpClient().newBuilder().readTimeout(10000, TimeUnit.SECONDS)
            .connectTimeout(10000, TimeUnit.SECONDS).writeTimeout(10000, TimeUnit.SECONDS).build()
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(Config.BASE_URL).client(okHttpClient)
                .addConverterFactory(
                    GsonConverterFactory.create()
                ).build()
        return retrofit.create(clazz)
    }
}