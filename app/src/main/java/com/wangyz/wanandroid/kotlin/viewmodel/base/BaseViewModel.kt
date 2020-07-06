package com.wangyz.wanandroid.kotlin.viewmodel.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<V : ViewDataBinding> : ViewModel() {
    lateinit var binding: V
}