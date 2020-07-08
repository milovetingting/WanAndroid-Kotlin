package com.wangyz.wanandroid.kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wangyz.wanandroid.kotlin.adapter.ArchitectureAdapter
import com.wangyz.wanandroid.kotlin.bean.ArchitectureResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentArchitectureBinding
import com.wangyz.wanandroid.kotlin.extLaunch
import com.wangyz.wanandroid.kotlin.model.ArchitectureModel
import com.wangyz.wanandroid.kotlin.viewmodel.base.BaseViewModel

class ArchitectureViewModel : BaseViewModel<FragmentArchitectureBinding>() {

    var data: MutableLiveData<List<ArchitectureResponse>> = MutableLiveData()

    lateinit var adapter: ArchitectureAdapter

    fun loadArchitecture() {
        extLaunch({
            data.postValue(ArchitectureModel.loadArchitecture().data)
        })
    }
}