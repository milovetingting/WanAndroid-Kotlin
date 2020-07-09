package com.wangyz.wanandroid.kotlin

object Config {
    const val TAG = "WanAndroid"
    const val PAGE_SIZE = 20
    const val BASE_URL = "https://www.wanandroid.com"
    const val URL_BANNER = "/banner/json"
    const val URL_HOME = "/article/list/{page}/json"
    const val URL_ARCHITECTURE = "/tree/json"
    const val URL_ARCHITECTURE_DETAIL = "/article/list/{page}/json"
    const val URL_PROJECT = "/project/tree/json"
    const val URL_PROJECT_DETAIL = "/project/list/{page}/json"
    const val URL_WX = "/wxarticle/chapters/json  "
    const val URL_WX_DETAIL = "/wxarticle/list/{cid}/{page}/json"
}