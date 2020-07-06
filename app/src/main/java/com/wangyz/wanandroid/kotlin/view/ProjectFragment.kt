package com.wangyz.wanandroid.kotlin.view


import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.databinding.FragmentProjectBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.viewmodel.ProjectViewModel

/**
 * 项目Fragment
 */
class ProjectFragment : BaseFragment<FragmentProjectBinding, ProjectViewModel>() {
    override val classT: Class<ProjectViewModel>
        get() = ProjectViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_project

}
