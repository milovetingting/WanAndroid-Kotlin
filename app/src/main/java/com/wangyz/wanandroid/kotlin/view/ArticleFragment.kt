package com.wangyz.wanandroid.kotlin.view


import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.just.agentweb.AgentWeb
import com.wangyz.wanandroid.kotlin.BR
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.bean.HomeResponse
import com.wangyz.wanandroid.kotlin.databinding.FragmentArticleBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.viewmodel.ArticleViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ShareViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus

/**
 * 文章Fragment
 */
class ArticleFragment : BaseFragment<FragmentArticleBinding, ArticleViewModel>() {

    override val classT: Class<ArticleViewModel>
        get() = ArticleViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_article

    override fun initView() {
        val viewModel = ViewModelBus.INSTANCE.get(classT)
        viewModel?.url = arguments?.getString("url")!!
        viewModel?.agentWeb = AgentWeb.with(this).setAgentWebParent(
            viewModel?.binding?.articleContainer!!,
            LinearLayout.LayoutParams(-1, -1)
        ).useDefaultIndicator().createAgentWeb().ready().go(viewModel.url)
        viewModel.agentWeb.agentWebSettings.webSettings.useWideViewPort = true
        viewModel.agentWeb.agentWebSettings.webSettings.loadWithOverviewMode = true
        val title = arguments!!.getString("title")
        viewModel.binding.setVariable(BR.title, title)
        ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.head.postValue(title)
    }

    override fun reInit() {
        val viewModel = ViewModelBus.INSTANCE.get(classT)
        viewModel?.url = arguments?.getString("url")!!
        viewModel?.agentWeb = AgentWeb.with(this).setAgentWebParent(
            viewModel?.binding?.articleContainer!!,
            LinearLayout.LayoutParams(-1, -1)
        ).useDefaultIndicator().createAgentWeb().ready().go(viewModel.url)
        val title = arguments!!.getString("title")
        viewModel.binding.setVariable(BR.title, title)
        ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.head.postValue(title)
    }

}
