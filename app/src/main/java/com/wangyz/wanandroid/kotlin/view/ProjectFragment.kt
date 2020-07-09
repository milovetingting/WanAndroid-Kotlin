package com.wangyz.wanandroid.kotlin.view


import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.adapter.ProjectAdapter
import com.wangyz.wanandroid.kotlin.bean.ProjectResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentProjectBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.view.custom.SpaceItemDecoration
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ProjectViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ShareViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus

/**
 * 项目Fragment
 */
class ProjectFragment : BaseFragment<FragmentProjectBinding, ProjectViewModel>() {

    override val classT: Class<ProjectViewModel>
        get() = ProjectViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_project

    @SuppressLint("WrongConstant")
    override fun initView() {
        val viewModel = ViewModelBus.INSTANCE.get(ProjectViewModel::class.java)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = OrientationHelper.VERTICAL
        viewModel?.binding?.projectRv?.layoutManager = layoutManager
        viewModel?.binding?.projectRv?.addItemDecoration(
            SpaceItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.item_margin
                )
            )
        )
        viewModel?.binding?.projectRv?.addOnScrollListener(object :
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
        viewModel?.data?.observe(viewLifecycleOwner, Observer<List<ProjectResponse>> {
            viewModel?.adapter = ProjectAdapter(context)
            viewModel?.binding!!.projectRv.adapter = viewModel?.adapter
        })
        viewModel?.loadProject()
        ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.head.postValue(getString(R.string.tab_project))
    }

    override fun reInit() {
        ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.head.postValue(getString(R.string.tab_project))
        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.VISIBLE
    }

}
