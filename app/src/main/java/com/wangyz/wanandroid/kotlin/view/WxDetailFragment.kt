package com.wangyz.wanandroid.kotlin.view


import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.adapter.WxDetailAdapter
import com.wangyz.wanandroid.kotlin.bean.WxDetailResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentWxDetailBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.view.custom.SpaceItemDecoration
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ShareViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus
import com.wangyz.wanandroid.kotlin.viewmodel.WxDetailViewModel

/**
 * 公众号二级分类Fragment
 */
class WxDetailFragment : BaseFragment<FragmentWxDetailBinding, WxDetailViewModel>() {
    override val classT: Class<WxDetailViewModel>
        get() = WxDetailViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_wx_detail

    override fun initView() {
        val viewModel = ViewModelBus.INSTANCE.get(classT)

        viewModel!!.adapter = WxDetailAdapter()

        val cid = arguments?.getInt("cid")
        val name = arguments?.getString("name", "")!!
        viewModel.cid = cid!!
        viewModel.name = name!!
        viewModel.load()

        ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.head.postValue(
            getString(R.string.tab_wx) + "-" +
                    viewModel.name
        )

        viewModel.data.observe(viewLifecycleOwner,
            Observer<PagedList<WxDetailResponse.DataBean>> {
                viewModel.adapter.submitList(it)
            })

        viewModel.binding.wxDetailRv.adapter = viewModel.adapter
        viewModel.binding.wxDetailRv.layoutManager = LinearLayoutManager(context)
        viewModel.binding.wxDetailRv.addItemDecoration(
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
            viewModel.adapter = WxDetailAdapter()
            viewModel.binding.wxDetailRv.adapter = viewModel.adapter
            viewModel.cid = cid
            viewModel.name = name
            viewModel.load()

            viewModel.data.observe(viewLifecycleOwner,
                Observer<PagedList<WxDetailResponse.DataBean>> {
                    viewModel.adapter.submitList(it)
                })
        }

        ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.head.postValue(
            getString(R.string.tab_wx) + "-" +
                    name
        )

        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.GONE
    }


}
