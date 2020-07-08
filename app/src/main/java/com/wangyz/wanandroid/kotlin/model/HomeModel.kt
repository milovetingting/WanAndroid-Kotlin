package com.wangyz.wanandroid.kotlin.model

import com.wangyz.wanandroid.kotlin.api.API
import com.wangyz.wanandroid.kotlin.bean.BannerResponse
import com.wangyz.wanandroid.kotlin.bean.ResponseWrapper
import com.wangyz.wanandroid.kotlin.net.APIClient

object HomeModel {

    suspend fun loadBanner(): ResponseWrapper<List<BannerResponse>> {
        return APIClient.INSTANCE.retrofit(API::class.java).loadBanner()
    }
}