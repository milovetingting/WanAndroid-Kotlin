package com.wangyz.wanandroid.kotlin.api

import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.bean.BannerResponse
import com.wangyz.wanandroid.kotlin.bean.HomeResponse
import com.wangyz.wanandroid.kotlin.bean.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET(Config.URL_BANNER)
    suspend fun loadBanner(): ResponseWrapper<List<BannerResponse>>

    @GET(Config.URL_HOME)
    suspend fun loadHome(@Path("page") page: Int): ResponseWrapper<HomeResponse>

}