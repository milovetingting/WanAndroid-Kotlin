package com.wangyz.wanandroid.kotlin.view


import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.adapter.WxAdapter
import com.wangyz.wanandroid.kotlin.bean.WxResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentWxBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.view.custom.SpaceItemDecoration
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ShareViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus
import com.wangyz.wanandroid.kotlin.viewmodel.WxViewModel

/**
 * 公众号Fragment
 */
class WxFragment : BaseFragment<FragmentWxBinding, WxViewModel>() {

    override val classT: Class<WxViewModel>
        get() = WxViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_wx

    @SuppressLint("WrongConstant")
    override fun initView() {
        val viewModel = ViewModelBus.INSTANCE.get(WxViewModel::class.java)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = OrientationHelper.VERTICAL
        viewModel?.binding?.wxRv?.layoutManager = layoutManager
        viewModel?.binding?.wxRv?.addItemDecoration(
            SpaceItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.item_margin
                )
            )
        )
        viewModel?.binding?.wxRv?.addOnScrollListener(object :
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
        viewModel?.data?.observe(viewLifecycleOwner, Observer<List<WxResponse>> {
            viewModel?.adapter = WxAdapter(context)
            viewModel?.binding!!.wxRv.adapter = viewModel?.adapter
        })
        viewModel?.loadWx()
        ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.head.postValue(getString(R.string.tab_wx))
    }

    override fun reInit() {
        ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.head.postValue(getString(R.string.tab_wx))
        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.VISIBLE
    }

}
