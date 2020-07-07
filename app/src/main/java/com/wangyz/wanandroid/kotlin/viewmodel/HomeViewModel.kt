package com.wangyz.wanandroid.kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.adapter.HomeAdapter
import com.wangyz.wanandroid.kotlin.bean.BannerResponse
import com.wangyz.wanandroid.kotlin.bean.HomeResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentHomeBinding
import com.wangyz.wanandroid.kotlin.extLaunch
import com.wangyz.wanandroid.kotlin.model.HomeModel
import com.wangyz.wanandroid.kotlin.repository.HomeDataSourceFactory
import com.wangyz.wanandroid.kotlin.viewmodel.base.BaseViewModel

class HomeViewModel : BaseViewModel<FragmentHomeBinding>() {
    var banner: MutableLiveData<List<BannerResponse>> = MutableLiveData()
    var images = mutableListOf<String>()
    var titles = mutableListOf<String>()
    var urls = mutableListOf<String>()
    var data: LiveData<PagedList<HomeResponse.DataBean>>
    lateinit var adapter: HomeAdapter

    init {
        val factory = HomeDataSourceFactory()
        data = LivePagedListBuilder(factory, Config.PAGE_SIZE).build()
    }

    fun loadBanner() {
        extLaunch({
            HomeModel.loadBanner().data.forEach {
                images.add(it.imagePath)
                titles.add(it.title)
                urls.add(it.url)
            }
            banner.postValue(HomeModel.loadBanner().data)
        })
    }
}