package com.wangyz.wanandroid.kotlin.repository

import androidx.paging.DataSource
import com.wangyz.wanandroid.kotlin.bean.HomeResponse

class HomeDataSourceFactory : DataSource.Factory<Int, HomeResponse.DataBean>() {
    override fun create(): DataSource<Int, HomeResponse.DataBean> = HomeDataSource()
}