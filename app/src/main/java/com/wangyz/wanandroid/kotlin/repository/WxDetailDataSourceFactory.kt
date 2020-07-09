package com.wangyz.wanandroid.kotlin.repository

import androidx.paging.DataSource
import com.wangyz.wanandroid.kotlin.bean.WxDetailResponse

class WxDetailDataSourceFactory(private val cid: Int) :
    DataSource.Factory<Int, WxDetailResponse.DataBean>() {
    override fun create(): DataSource<Int, WxDetailResponse.DataBean> =
        WxDetailDataSource(cid)
}