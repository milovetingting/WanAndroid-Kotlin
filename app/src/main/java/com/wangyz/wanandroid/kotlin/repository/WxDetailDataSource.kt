package com.wangyz.wanandroid.kotlin.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.bean.WxDetailResponse
import com.wangyz.wanandroid.kotlin.extLaunch
import com.wangyz.wanandroid.kotlin.net.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WxDetailDataSource(private val cid: Int) :
    PageKeyedDataSource<Int, WxDetailResponse.DataBean>() {

    private var page = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, WxDetailResponse.DataBean>
    ) {
        load(callback)
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, WxDetailResponse.DataBean>
    ) {
        page++
        loadNext(callback)
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, WxDetailResponse.DataBean>
    ) {

    }

    private fun load(callback: LoadInitialCallback<Int, WxDetailResponse.DataBean>) {
        extLaunch({
            val data =
                APIService.INSTANCE.service.loadWxDetail(page, cid)
            callback.onResult(
                data?.data?.datas as MutableList<WxDetailResponse.DataBean>,
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

    private fun loadNext(callback: LoadCallback<Int, WxDetailResponse.DataBean>) {
        extLaunch({
            val data =
                APIService.INSTANCE.service.loadWxDetail(page, cid)
            callback.onResult(
                data?.data?.datas as MutableList<WxDetailResponse.DataBean>,
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