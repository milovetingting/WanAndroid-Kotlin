package com.wangyz.wanandroid.kotlin.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.api.API
import com.wangyz.wanandroid.kotlin.bean.HomeResponse
import com.wangyz.wanandroid.kotlin.extLaunch
import com.wangyz.wanandroid.kotlin.net.APIClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeDataSource : PageKeyedDataSource<Int, HomeResponse.DataBean>() {

    private var page = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, HomeResponse.DataBean>
    ) {
        load(callback)
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, HomeResponse.DataBean>
    ) {
        page++
        loadNext(callback)
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, HomeResponse.DataBean>
    ) {

    }

    private fun load(callback: LoadInitialCallback<Int, HomeResponse.DataBean>) {
        extLaunch({
            val data = APIClient.INSTANCE.initRetrofit(API::class.java).loadHome(page)
            callback.onResult(
                data?.data?.datas as MutableList<HomeResponse.DataBean>,
                page - 1, page
            )
        }, { e ->
            GlobalScope.launch(Dispatchers.Main) {
                Log.e(Config.TAG, "load failed:${e.message}")
            }
        }, {
            GlobalScope.launch(Dispatchers.Main) {
                Log.d(Config.TAG, "load complete")
            }

        })
    }

    private fun loadNext(callback: LoadCallback<Int, HomeResponse.DataBean>) {
        extLaunch({
            val data = APIClient.INSTANCE.initRetrofit(API::class.java).loadHome(page)
            callback.onResult(
                data?.data?.datas as MutableList<HomeResponse.DataBean>,
                page
            )
        }, { e ->
            GlobalScope.launch(Dispatchers.Main) {
                Log.e(Config.TAG, "load failed:${e.message}")
            }
        }, {
            GlobalScope.launch(Dispatchers.Main) {
                Log.d(Config.TAG, "load complete")
            }

        })
    }

}