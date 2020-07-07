package com.wangyz.wanandroid.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.adapter.HomeAdapter
import com.wangyz.wanandroid.kotlin.bean.HomeResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentHomeBinding
import com.wangyz.wanandroid.kotlin.repository.HomeDataSourceFactory
import com.wangyz.wanandroid.kotlin.viewmodel.base.BaseViewModel

class HomeViewModel : BaseViewModel<FragmentHomeBinding>() {
    var data: LiveData<PagedList<HomeResponse.DataBean>>
    lateinit var adapter: HomeAdapter

    init {
        val factory = HomeDataSourceFactory()
        data = LivePagedListBuilder(factory, Config.PAGE_SIZE).build()
    }
}