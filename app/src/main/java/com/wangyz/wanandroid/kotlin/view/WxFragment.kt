package com.wangyz.wanandroid.kotlin.view


import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.databinding.FragmentWxBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.viewmodel.WxViewModel

/**
 * 公众号Fragment
 */
class WxFragment : BaseFragment<FragmentWxBinding, WxViewModel>() {
    override val classT: Class<WxViewModel>
        get() = WxViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_wx

}
