package com.wangyz.wanandroid.kotlin.net

abstract class Response<T> {
    abstract fun success(data: T?)
    abstract fun failure(errorMsg: String?)
}