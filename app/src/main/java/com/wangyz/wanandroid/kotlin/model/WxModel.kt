package com.wangyz.wanandroid.kotlin.model

import com.wangyz.wanandroid.kotlin.api.API
import com.wangyz.wanandroid.kotlin.bean.ResponseWrapper
import com.wangyz.wanandroid.kotlin.bean.WxResponse
import com.wangyz.wanandroid.kotlin.net.APIClient

object WxModel {

    suspend fun loadWx(): ResponseWrapper<List<WxResponse>> {
        return APIClient.INSTANCE.retrofit(API::class.java).loadWx()
    }

}