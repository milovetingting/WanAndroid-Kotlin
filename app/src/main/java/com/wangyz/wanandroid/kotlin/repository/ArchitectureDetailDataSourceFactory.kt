package com.wangyz.wanandroid.kotlin.repository

import androidx.paging.DataSource
import com.wangyz.wanandroid.kotlin.bean.ArchitectureDetailResponse

class ArchitectureDetailDataSourceFactory(private val cid:Int) :
    DataSource.Factory<Int, ArchitectureDetailResponse.DataBean>() {
    override fun create(): DataSource<Int, ArchitectureDetailResponse.DataBean> =
        ArchitectureDetailDataSource(cid)
}