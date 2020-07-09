package com.wangyz.wanandroid.kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wangyz.wanandroid.kotlin.adapter.ProjectAdapter
import com.wangyz.wanandroid.kotlin.bean.ProjectResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentProjectBinding
import com.wangyz.wanandroid.kotlin.extLaunch
import com.wangyz.wanandroid.kotlin.model.ProjectModel
import com.wangyz.wanandroid.kotlin.viewmodel.base.BaseViewModel

class ProjectViewModel : BaseViewModel<FragmentProjectBinding>() {
    var data: MutableLiveData<List<ProjectResponse>> = MutableLiveData()

    lateinit var adapter: ProjectAdapter

    fun loadProject() {
        extLaunch({
            data.postValue(ProjectModel.loadProject().data)
        })
    }
}