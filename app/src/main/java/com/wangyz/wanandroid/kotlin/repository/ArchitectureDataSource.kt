package com.wangyz.wanandroid.kotlin.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.api.API
import com.wangyz.wanandroid.kotlin.bean.ArchitectureDetailResponse
import com.wangyz.wanandroid.kotlin.extLaunch
import com.wangyz.wanandroid.kotlin.net.APIClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArchitectureDataSource(private val cid: Int) :
    PageKeyedDataSource<Int, ArchitectureDetailResponse.DataBean>() {

    private var page = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ArchitectureDetailResponse.DataBean>
    ) {
        load(callback)
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ArchitectureDetailResponse.DataBean>
    ) {
        page++
        loadNext(callback)
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ArchitectureDetailResponse.DataBean>
    ) {

    }

    private fun load(callback: LoadInitialCallback<Int, ArchitectureDetailResponse.DataBean>) {
        extLaunch({
            val data =
                APIClient.INSTANCE.retrofit(API::class.java).loadArchitectureDetail(page, cid)
            callback.onResult(
                data?.data?.datas as MutableList<ArchitectureDetailResponse.DataBean>,
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

    private fun loadNext(callback: LoadCallback<Int, ArchitectureDetailResponse.DataBean>) {
        extLaunch({
            val data =
                APIClient.INSTANCE.retrofit(API::class.java).loadArchitectureDetail(page, cid)
            callback.onResult(
                data?.data?.datas as MutableList<ArchitectureDetailResponse.DataBean>,
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