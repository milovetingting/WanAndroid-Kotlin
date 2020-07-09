package com.wangyz.wanandroid.kotlin.model

import com.wangyz.wanandroid.kotlin.api.API
import com.wangyz.wanandroid.kotlin.bean.ResponseWrapper
import com.wangyz.wanandroid.kotlin.bean.WxResponse
import com.wangyz.wanandroid.kotlin.net.APIService

object WxModel {

    suspend fun loadWx(): ResponseWrapper<List<WxResponse>> {
        return APIService.INSTANCE.service.loadWx()
    }

}