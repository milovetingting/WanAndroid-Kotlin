package com.wangyz.wanandroid.kotlin.viewmodel

import com.just.agentweb.AgentWeb
import com.wangyz.wanandroid.kotlin.databinding.FragmentArticleBinding
import com.wangyz.wanandroid.kotlin.viewmodel.base.BaseViewModel

class ArticleViewModel : BaseViewModel<FragmentArticleBinding>() {

    lateinit var agentWeb: AgentWeb

    lateinit var url: String
}