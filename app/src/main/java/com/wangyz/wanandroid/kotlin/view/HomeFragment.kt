package com.wangyz.wanandroid.kotlin.view


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.adapter.HomeAdapter
import com.wangyz.wanandroid.kotlin.bean.BannerResponse
import com.wangyz.wanandroid.kotlin.bean.HomeResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentHomeBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.view.custom.GlideImageLoader
import com.wangyz.wanandroid.kotlin.view.custom.SpaceItemDecoration
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.HomeViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ShareViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer

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
        viewModel.binding.homeRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

        viewModel.binding.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        viewModel.binding.banner.setBannerAnimation(Transformer.Tablet)
        viewModel.binding.banner.setImageLoader(GlideImageLoader())
        viewModel.binding.banner.setOnBannerListener {
            val bundle = Bundle()
            bundle.putString("title", viewModel.titles[it])
            bundle.putString("url", viewModel.urls[it])
            Navigation.findNavController(viewModel.binding.root)
                .navigate(R.id.articleFragment, bundle)
            ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
                View.GONE
        }
        viewModel.banner.observe(viewLifecycleOwner, Observer<List<BannerResponse>> {
            viewModel.binding.banner.setImages(viewModel.images)
            viewModel.binding.banner.setBannerTitles(viewModel.titles)
            viewModel.binding.banner.start()
        })
        viewModel.loadBanner()

        ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.head.postValue(getString(R.string.tab_home))
    }

    override fun reInit() {
        ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.head.postValue(getString(R.string.tab_home))
        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.VISIBLE
    }

}
