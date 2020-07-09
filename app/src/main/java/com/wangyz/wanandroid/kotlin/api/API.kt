package com.wangyz.wanandroid.kotlin.api

import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.bean.*
import retrofit2.http.*

interface API {

    @GET(Config.URL_BANNER)
    suspend fun loadBanner(): ResponseWrapper<List<BannerResponse>>

    @GET(Config.URL_HOME)
    suspend fun loadHome(@Path("page") page: Int): ResponseWrapper<HomeResponse>

    @GET(Config.URL_ARCHITECTURE)
    suspend fun loadArchitecture(): ResponseWrapper<List<ArchitectureResponse>>

    @GET(Config.URL_ARCHITECTURE_DETAIL)
    suspend fun loadArchitectureDetail(@Path("page") page: Int, @Query("cid") cid: Int): ResponseWrapper<ArchitectureDetailResponse>

    @GET(Config.URL_PROJECT)
    suspend fun loadProject(): ResponseWrapper<List<ProjectResponse>>

    @GET(Config.URL_PROJECT_DETAIL)
    suspend fun loadProjectDetail(@Path("page") page: Int, @Query("cid") cid: Int): ResponseWrapper<ProjectDetailResponse>

    @GET(Config.URL_WX)
    suspend fun loadWx(): ResponseWrapper<List<WxResponse>>

    @GET(Config.URL_WX_DETAIL)
    suspend fun loadWxDetail(@Path("page") page: Int, @Path("cid") cid: Int): ResponseWrapper<WxDetailResponse>

    @POST(Config.URL_LOGIN)
    @FormUrlEncoded
    suspend fun login(@Field("username") username: String, @Field("password") password: String): ResponseWrapper<LoginResponse>

}