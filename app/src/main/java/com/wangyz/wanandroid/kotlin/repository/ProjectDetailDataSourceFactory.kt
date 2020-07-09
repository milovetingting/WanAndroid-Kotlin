package com.wangyz.wanandroid.kotlin.repository

import androidx.paging.DataSource
import com.wangyz.wanandroid.kotlin.bean.ProjectDetailResponse

class ProjectDetailDataSourceFactory(private val cid: Int) :
    DataSource.Factory<Int, ProjectDetailResponse.DataBean>() {
    override fun create(): DataSource<Int, ProjectDetailResponse.DataBean> =
        ProjectDetailDataSource(cid)
}