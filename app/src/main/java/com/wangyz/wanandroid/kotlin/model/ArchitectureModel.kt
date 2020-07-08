package com.wangyz.wanandroid.kotlin.model

import com.wangyz.wanandroid.kotlin.api.API
import com.wangyz.wanandroid.kotlin.bean.ArchitectureResponse
import com.wangyz.wanandroid.kotlin.bean.ResponseWrapper
import com.wangyz.wanandroid.kotlin.net.APIClient

object ArchitectureModel {

    suspend fun loadArchitecture(): ResponseWrapper<List<ArchitectureResponse>> {
        return APIClient.INSTANCE.retrofit(API::class.java).loadArchitecture()
    }

}