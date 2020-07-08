package com.wangyz.wanandroid.kotlin.view


import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.adapter.ArchitectureAdapter
import com.wangyz.wanandroid.kotlin.bean.ArchitectureResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentArchitectureBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.view.custom.SpaceItemDecoration
import com.wangyz.wanandroid.kotlin.viewmodel.ArchitectureViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus

/**
 * 体系Fragment
 */
class ArchitectureFragment : BaseFragment<FragmentArchitectureBinding, ArchitectureViewModel>() {

    override val classT: Class<ArchitectureViewModel>
        get() = ArchitectureViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_architecture

    @SuppressLint("WrongConstant")
    override fun initView() {
        val viewModel = ViewModelBus.INSTANCE.get(ArchitectureViewModel::class.java)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = OrientationHelper.VERTICAL
        viewModel?.binding?.architectureRv?.layoutManager = layoutManager
        viewModel?.binding?.architectureRv?.addItemDecoration(
            SpaceItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.item_margin
                )
            )
        )
        viewModel?.binding?.architectureRv?.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    ViewModelBus.INSTANCE.get(BottomViewModel::class.java)
                        ?.binding?.root?.visibility =
                        View.GONE
                } else {
                    ViewModelBus.INSTANCE.get(BottomViewModel::class.java)
                        ?.binding?.root?.visibility =
                        View.VISIBLE
                }
            }
        })
        viewModel?.data?.observe(viewLifecycleOwner, Observer<List<ArchitectureResponse>> {
            viewModel?.adapter = ArchitectureAdapter(context)
            viewModel?.binding!!.architectureRv.adapter = viewModel?.adapter
        })
        viewModel?.loadArchitecture()
    }

}
