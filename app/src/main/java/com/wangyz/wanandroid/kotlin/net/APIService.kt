package com.wangyz.wanandroid.kotlin.net

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.wangyz.wanandroid.kotlin.App
import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.api.API
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class APIService {

    companion object {
        val INSTANCE = APIService()
    }

    val service by lazy {
        createService(API::class.java, Config.BASE_URL)
    }

    private fun <T> createService(clazz: Class<T>, baseUrl: String): T {
        val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.app))
        val okHttpClient = OkHttpClient().newBuilder().readTimeout(10000, TimeUnit.SECONDS)
            .connectTimeout(10000, TimeUnit.SECONDS).writeTimeout(10000, TimeUnit.SECONDS)
            .cookieJar(cookieJar).build()
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
                .addConverterFactory(
                    GsonConverterFactory.create()
                ).build()
        return retrofit.create(clazz)
    }
}