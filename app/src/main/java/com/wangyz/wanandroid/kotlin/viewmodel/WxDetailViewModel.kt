package com.wangyz.wanandroid.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.adapter.WxDetailAdapter
import com.wangyz.wanandroid.kotlin.bean.WxDetailResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentWxDetailBinding
import com.wangyz.wanandroid.kotlin.repository.WxDetailDataSourceFactory
import com.wangyz.wanandroid.kotlin.viewmodel.base.BaseViewModel

class WxDetailViewModel : BaseViewModel<FragmentWxDetailBinding>() {
    var cid: Int = 0
    var name: String = ""
    lateinit var data: LiveData<PagedList<WxDetailResponse.DataBean>>
    lateinit var adapter: WxDetailAdapter

    fun load() {
        val factory = WxDetailDataSourceFactory(cid)
        data = LivePagedListBuilder(factory, Config.PAGE_SIZE).build()
    }
}