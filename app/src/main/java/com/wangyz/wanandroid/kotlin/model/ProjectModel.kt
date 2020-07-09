package com.wangyz.wanandroid.kotlin.model

import com.wangyz.wanandroid.kotlin.api.API
import com.wangyz.wanandroid.kotlin.bean.ProjectResponse
import com.wangyz.wanandroid.kotlin.bean.ResponseWrapper
import com.wangyz.wanandroid.kotlin.net.APIService

object ProjectModel {

    suspend fun loadProject(): ResponseWrapper<List<ProjectResponse>> {
        return APIService.INSTANCE.service.loadProject()
    }

}