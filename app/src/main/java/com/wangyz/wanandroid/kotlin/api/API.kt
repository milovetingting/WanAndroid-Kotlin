package com.wangyz.wanandroid.kotlin.api

import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.bean.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET(Config.URL_BANNER)
    suspend fun loadBanner(): ResponseWrapper<List<BannerResponse>>

    @GET(Config.URL_HOME)
    suspend fun loadHome(@Path("page") page: Int): ResponseWrapper<HomeResponse>

    @GET(Config.URL_ARCHITECTURE)
    suspend fun loadArchitecture(): ResponseWrapper<List<ArchitectureResponse>>

    @GET(Config.URL_ARCHITECTURE_DETAIL)
    suspend fun loadArchitectureDetail(@Path("page") page: Int, @Query("cid") cid: Int): ResponseWrapper<ArchitectureDetailResponse>

}