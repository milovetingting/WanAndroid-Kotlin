package com.wangyz.wanandroid.kotlin.view


import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.databinding.FragmentMenuBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.viewmodel.MenuViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ShareViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus

/**
 * 菜单Fragment
 */
class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {
    override val classT: Class<MenuViewModel>
        get() = MenuViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_menu

    override fun initView() {
        val viewModel = ViewModelBus.INSTANCE.get(MenuViewModel::class.java)
        val shareViewModel = ViewModelBus.INSTANCE.get(ShareViewModel::class.java)
        viewModel?.binding!!.data = shareViewModel
    }


}
