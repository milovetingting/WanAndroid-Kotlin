package com.wangyz.wanandroid.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.adapter.ProjectDetailAdapter
import com.wangyz.wanandroid.kotlin.bean.ProjectDetailResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentProjectDetailBinding
import com.wangyz.wanandroid.kotlin.repository.ProjectDetailDataSourceFactory
import com.wangyz.wanandroid.kotlin.viewmodel.base.BaseViewModel

class ProjectDetailViewModel : BaseViewModel<FragmentProjectDetailBinding>() {
    var cid: Int = 0
    var name: String = ""
    lateinit var data: LiveData<PagedList<ProjectDetailResponse.DataBean>>
    lateinit var adapter: ProjectDetailAdapter

    fun load() {
        val factory = ProjectDetailDataSourceFactory(cid)
        data = LivePagedListBuilder(factory, Config.PAGE_SIZE).build()
    }
}