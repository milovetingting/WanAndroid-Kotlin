package com.wangyz.wanandroid.kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel : ViewModel() {
    var head: MutableLiveData<String> = MutableLiveData()
    var login = false
}