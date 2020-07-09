package com.wangyz.wanandroid.kotlin.binding

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.bean.*
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ShareViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus

class ClickProxy {
    fun openArticle(view: View, article: HomeResponse.DataBean) {
        val bundle = Bundle()
        bundle.putString("title", article.title)
        bundle.putString("url", article.link)
        Navigation.findNavController(view).navigate(R.id.articleFragment, bundle)
        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.GONE
    }

    fun openArticle(view: View, article: ArchitectureDetailResponse.DataBean) {
        val bundle = Bundle()
        bundle.putString("title", article.title)
        bundle.putString("url", article.link)
        Navigation.findNavController(view).navigate(R.id.articleFragment, bundle)
        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.GONE
    }

    fun openProjectDetail(view: View, project: ProjectResponse) {
        val bundle = Bundle()
        bundle.putInt("cid", project.id)
        bundle.putString("name", project.name)
        Navigation.findNavController(view).navigate(R.id.projectDetailFragment, bundle)
        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.GONE
    }

    fun openArticle(view: View, article: ProjectDetailResponse.DataBean) {
        val bundle = Bundle()
        bundle.putString("title", article.title)
        bundle.putString("url", article.link)
        Navigation.findNavController(view).navigate(R.id.articleFragment, bundle)
        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.GONE
    }

    fun openWxDetail(view: View, project: WxResponse) {
        val bundle = Bundle()
        bundle.putInt("cid", project.id)
        bundle.putString("name", project.name)
        Navigation.findNavController(view).navigate(R.id.wxDetailFragment, bundle)
        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.GONE
    }

    fun openArticle(view: View, article: WxDetailResponse.DataBean) {
        val bundle = Bundle()
        bundle.putString("title", article.title)
        bundle.putString("url", article.link)
        Navigation.findNavController(view).navigate(R.id.articleFragment, bundle)
        ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
            View.GONE
    }

    fun collect(view: View, id: Int, collect: Boolean) {
        val viewModel = ViewModelBus.INSTANCE.get(ShareViewModel::class.java)
        if (!viewModel?.login!!) {
            Navigation.findNavController(view).navigate(R.id.loginFragment)
            ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
                View.GONE
        } else {
            Toast.makeText(view.context, "该功能暂未实现", Toast.LENGTH_SHORT).show()
        }
    }
}