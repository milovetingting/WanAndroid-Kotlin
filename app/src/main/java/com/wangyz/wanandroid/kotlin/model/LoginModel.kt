package com.wangyz.wanandroid.kotlin.model

import com.wangyz.wanandroid.kotlin.api.API
import com.wangyz.wanandroid.kotlin.bean.LoginResponse
import com.wangyz.wanandroid.kotlin.bean.ResponseWrapper
import com.wangyz.wanandroid.kotlin.net.APIService

object LoginModel {
    suspend fun login(username: String, password: String): ResponseWrapper<LoginResponse> {
        return APIService.INSTANCE.service.login(username, password)
    }
}