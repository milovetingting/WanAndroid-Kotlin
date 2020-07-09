package com.wangyz.wanandroid.kotlin.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.api.API
import com.wangyz.wanandroid.kotlin.bean.ProjectDetailResponse
import com.wangyz.wanandroid.kotlin.extLaunch
import com.wangyz.wanandroid.kotlin.net.APIClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProjectDetailDataSource(private val cid: Int) :
    PageKeyedDataSource<Int, ProjectDetailResponse.DataBean>() {

    private var page = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ProjectDetailResponse.DataBean>
    ) {
        load(callback)
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ProjectDetailResponse.DataBean>
    ) {
        page++
        loadNext(callback)
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ProjectDetailResponse.DataBean>
    ) {

    }

    private fun load(callback: LoadInitialCallback<Int, ProjectDetailResponse.DataBean>) {
        extLaunch({
            val data =
                APIClient.INSTANCE.retrofit(API::class.java).loadProjectDetail(page, cid)
            callback.onResult(
                data?.data?.datas as MutableList<ProjectDetailResponse.DataBean>,
                page - 1, page
            )
        }, { e ->
            GlobalScope.launch(Dispatchers.Main) {
                Log.e(Config.TAG, "load failed:${e.message}")
            }
        }, {
            GlobalScope.launch(Dispatchers.Main) {

            }

        })
    }

    private fun loadNext(callback: LoadCallback<Int, ProjectDetailResponse.DataBean>) {
        extLaunch({
            val data =
                APIClient.INSTANCE.retrofit(API::class.java).loadProjectDetail(page, cid)
            callback.onResult(
                data?.data?.datas as MutableList<ProjectDetailResponse.DataBean>,
                page
            )
        }, { e ->
            GlobalScope.launch(Dispatchers.Main) {
                Log.e(Config.TAG, "load failed:${e.message}")
            }
        }, {
            GlobalScope.launch(Dispatchers.Main) {

            }

        })
    }

}