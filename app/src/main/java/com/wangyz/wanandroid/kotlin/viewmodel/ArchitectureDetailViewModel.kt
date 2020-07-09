package com.wangyz.wanandroid.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.adapter.ArchitectureDetailAdapter
import com.wangyz.wanandroid.kotlin.bean.ArchitectureDetailResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentArchitectureDetailBinding
import com.wangyz.wanandroid.kotlin.repository.ArchitectureDetailDataSourceFactory
import com.wangyz.wanandroid.kotlin.viewmodel.base.BaseViewModel

class ArchitectureDetailViewModel : BaseViewModel<FragmentArchitectureDetailBinding>() {
    var cid: Int = 0
    var name: String = ""
    lateinit var data: LiveData<PagedList<ArchitectureDetailResponse.DataBean>>
    lateinit var adapter: ArchitectureDetailAdapter

    fun load() {
        val factory = ArchitectureDetailDataSourceFactory(cid)
        data = LivePagedListBuilder(factory, Config.PAGE_SIZE).build()
    }
}