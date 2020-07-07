package com.wangyz.wanandroid.kotlin.bean

data class ResponseWrapper<T>(val data: T, val errorCode: Int, val errorMsg: String)