package com.wangyz.wanandroid.kotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class ViewModelBus {

    private var bus: MutableMap<String, ViewModel> = mutableMapOf()

    companion object {
        var INSTANCE = ViewModelBus()
    }

    @Synchronized
    fun <T : ViewModel> provide(owner: ViewModelStoreOwner, type: Class<T>): T {
        if (!bus.containsKey(type.name)!!) {
            bus[type.name] =
                ViewModelProvider(owner, ViewModelProvider.NewInstanceFactory()).get(type)
        }
        return bus[type.name] as T
    }

    @Synchronized
    fun <T : ViewModel> get(type: Class<T>): T? {
        return if (exist(type)) {
            bus[type.name] as T
        } else null
    }

    @Synchronized
    fun <T : ViewModel> exist(type: Class<T>): Boolean = bus.containsKey(type.name)

    @Synchronized
    fun release() {
        bus.clear()
    }

}