package com.wangyz.wanandroid.kotlin.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus
import com.wangyz.wanandroid.kotlin.viewmodel.base.BaseViewModel

abstract class BaseFragment<V : ViewDataBinding, T : BaseViewModel<V>> : Fragment() {

    /**
     * Class
     */
    protected abstract val classT: Class<T>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = bind(inflater, container)

    private fun bind(
        inflater: LayoutInflater, container: ViewGroup?
    ): View? {
        if (!ViewModelBus.INSTANCE.exist(classT)) {
            val viewModel: T = ViewModelBus.INSTANCE.provide(this, classT)
            viewModel.binding =
                DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            initView()
            return viewModel.binding.root
        }
        reInit()
        return ViewModelBus.INSTANCE.get(classT)?.binding?.root
    }

    /**
     * 获取布局ID
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化
     */
    open fun initView() {

    }

    /**
     * Fragment再次载入时的初始化
     */
    open fun reInit() {

    }

}