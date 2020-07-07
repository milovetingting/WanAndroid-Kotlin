package com.wangyz.wanandroid.kotlin.view


import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.databinding.FragmentArchitectureBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.viewmodel.ArchitectureViewModel

/**
 * 体系Fragment
 */
class ArchitectureFragment : BaseFragment<FragmentArchitectureBinding, ArchitectureViewModel>() {

    override val classT: Class<ArchitectureViewModel>
        get() = ArchitectureViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_architecture

}
