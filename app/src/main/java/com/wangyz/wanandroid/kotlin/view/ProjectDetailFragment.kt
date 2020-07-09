package com.wangyz.wanandroid.kotlin.view


import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.adapter.ProjectDetailAdapter
import com.wangyz.wanandroid.kotlin.bean.ProjectDetailResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentProjectDetailBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.view.custom.SpaceItemDecoration
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ProjectDetailViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ShareViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus

/**
 * 项目二级分类Fragment
 */
class ProjectDetailFragment : BaseFragment<FragmentProjectDetailBinding, ProjectDetailViewModel>() {
    override val classT: Class<ProjectDetailViewModel>
        get() = ProjectDetailViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_project_detail

    override fun initView() {
        val viewModel = ViewModelBus.INSTANCE.get(classT)

        viewModel!!.adapter = ProjectDetailAdapter()

        val cid = arguments?.getInt("cid")
        val name = arguments?.getString("name", "")!!
        viewModel.cid = cid!!
        viewModel.name = name!!
        viewModel.load()

        ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.head.postValue(
            getString(R.string.tab_project) + "-" +
                    viewModel.name
        )

        viewModel.data.observe(viewLifecycleOwner,
            Observer<PagedList<ProjectDetailResponse.DataBean>> {
                viewModel.adapter.submitList(it)
            })

        viewModel.binding.projectDetailRv.adapter = viewModel.adapter
        viewModel.binding.projectDetailRv.layoutManager = LinearLayoutManager(context)
        viewModel.binding.projectDetailRv.addItemDecoration(
            SpaceItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.item_margin
                )
            )
        )
    }

    override fun reInit() {

        val viewModel = ViewModelBus.INSTANCE.get(classT)

        val cid = arguments?.getInt("cid")!!
        val name = arguments?.getString("name", "")!!

        if (cid != viewModel!!.cid) {
            viewModel.adapter = ProjectDetailAdapter()
            viewModel.binding.projectDetailRv.adapter = viewModel.adapter
            viewModel.cid = cid
            viewModel.name = name
            viewModel.load()

            viewModel.data.observe(viewLifecycleOwner,
                Observer<PagedList<ProjectDetailResponse.DataBean>> {
                    viewModel.adapter.submitList(it)
                })
        }

        ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.head.postValue(
            getString(R.string.tab_project) + "-" +
                    name
        )

        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.GONE
    }

}
