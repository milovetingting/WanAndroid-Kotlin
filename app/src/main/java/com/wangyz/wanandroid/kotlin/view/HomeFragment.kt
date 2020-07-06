package com.wangyz.wanandroid.kotlin.view


import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.databinding.FragmentHomeBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.viewmodel.HomeViewModel

/**
 * 首页Fragment
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val classT: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_home

}
