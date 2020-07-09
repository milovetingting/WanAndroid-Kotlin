package com.wangyz.wanandroid.kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wangyz.wanandroid.kotlin.adapter.WxAdapter
import com.wangyz.wanandroid.kotlin.bean.WxResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentWxBinding
import com.wangyz.wanandroid.kotlin.extLaunch
import com.wangyz.wanandroid.kotlin.model.WxModel
import com.wangyz.wanandroid.kotlin.viewmodel.base.BaseViewModel

class WxViewModel : BaseViewModel<FragmentWxBinding>() {
    var data: MutableLiveData<List<WxResponse>> = MutableLiveData()

    lateinit var adapter: WxAdapter

    fun loadWx() {
        extLaunch({
            data.postValue(WxModel.loadWx().data)
        })
    }
}