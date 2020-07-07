package com.wangyz.wanandroid.kotlin

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun <T> T.extLaunch(
    block: suspend CoroutineScope.() -> Unit,
    error: suspend CoroutineScope.(Throwable) -> Unit = {
        Log.e(Config.TAG, "error:${it.message}")
        it.printStackTrace()
    },
    complete: suspend CoroutineScope.() -> Unit = {}
) {
    launchUI {
        try {
            block()
        } catch (e: Throwable) {
            error(e)
        } finally {
            complete()
        }
    }
}

private fun launchUI(block: suspend CoroutineScope.() -> Unit) {
    GlobalScope.launch { block() }
}