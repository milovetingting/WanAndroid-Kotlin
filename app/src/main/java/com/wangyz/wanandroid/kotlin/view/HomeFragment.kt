package com.wangyz.wanandroid.kotlin.view


import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.adapter.HomeAdapter
import com.wangyz.wanandroid.kotlin.bean.HomeResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentHomeBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.view.custom.SpaceItemDecoration
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.HomeViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus

/**
 * 首页Fragment
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val classT: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        val viewModel = ViewModelBus.INSTANCE.get(classT)
        viewModel!!.adapter = HomeAdapter()
        viewModel.data.observe(viewLifecycleOwner,
            Observer<PagedList<HomeResponse.DataBean>> {
                viewModel.adapter.submitList(it)
            })
        viewModel.binding.homeRv.adapter = viewModel.adapter
        viewModel.binding.homeRv.layoutManager = LinearLayoutManager(context)
        viewModel.binding.homeRv.addItemDecoration(
            SpaceItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.item_margin
                )
            )
        )
    }

    override fun reInit() {
        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.VISIBLE
    }

}
