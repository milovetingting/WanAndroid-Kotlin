package com.wangyz.wanandroid.kotlin.view


import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.adapter.ArchitectureDetailAdapter
import com.wangyz.wanandroid.kotlin.bean.ArchitectureDetailResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentArchitectureDetailBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.view.custom.SpaceItemDecoration
import com.wangyz.wanandroid.kotlin.viewmodel.ArchitectureDetailViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus

/**
 * A simple [Fragment] subclass.
 */
class ArchitectureDetailFragment :
    BaseFragment<FragmentArchitectureDetailBinding, ArchitectureDetailViewModel>() {

    override val classT: Class<ArchitectureDetailViewModel>
        get() = ArchitectureDetailViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_architecture_detail

    override fun initView() {
        val viewModel = ViewModelBus.INSTANCE.get(classT)

        viewModel!!.adapter = ArchitectureDetailAdapter()

        val cid = arguments?.getInt("cid")
        viewModel.cid = cid!!
        viewModel.load()

        viewModel.data.observe(viewLifecycleOwner,
            Observer<PagedList<ArchitectureDetailResponse.DataBean>> {
                viewModel.adapter.submitList(it)
            })

        viewModel.binding.architectureDetailRv.adapter = viewModel.adapter
        viewModel.binding.architectureDetailRv.layoutManager = LinearLayoutManager(context)
        viewModel.binding.architectureDetailRv.addItemDecoration(
            SpaceItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.item_margin
                )
            )
        )
    }

    override fun reInit() {

        val viewModel = ViewModelBus.INSTANCE.get(classT)

        val cid = arguments?.getInt("cid")
        if (cid != viewModel!!.cid) {
            viewModel.adapter = ArchitectureDetailAdapter()
            viewModel.binding.architectureDetailRv.adapter = viewModel.adapter
            viewModel.cid = cid!!
            viewModel.load()
            viewModel.data.observe(viewLifecycleOwner,
                Observer<PagedList<ArchitectureDetailResponse.DataBean>> {
                    viewModel.adapter.submitList(it)
                })
        }

        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.GONE
    }
}
