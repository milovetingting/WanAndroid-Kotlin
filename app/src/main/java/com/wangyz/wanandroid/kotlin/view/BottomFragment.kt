package com.wangyz.wanandroid.kotlin.view


import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.databinding.FragmentBottomBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus

/**
 * 底部导航Fragment
 */
class BottomFragment :
    BaseFragment<FragmentBottomBinding, BottomViewModel>() {

    override val classT: Class<BottomViewModel>
        get() = BottomViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_bottom

    override fun initView() {
        val viewModel = ViewModelBus.INSTANCE.get(classT)
        val navController: NavController =
            Navigation.findNavController(activity as Activity, R.id.content_fragment_host)
        NavigationUI.setupWithNavController(
            viewModel?.binding?.bottomNavigation!!,
            navController
        )
        viewModel.binding?.bottomNavigation.setOnNavigationItemReselectedListener {

        }
    }
}
