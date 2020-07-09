package com.wangyz.wanandroid.kotlin.utils

import android.content.Context
import com.wangyz.wanandroid.kotlin.Config

object SharedPreferencesUtil {

    fun <T> put(context: Context, key: String, value: T) {
        val sharedPreferences =
            context.getSharedPreferences(Config.CONFIG_NAME, Context.MODE_PRIVATE)
        when (value) {
            is Boolean -> {
                sharedPreferences.edit().putBoolean(key, value).commit()
            }
            is Float -> {
                sharedPreferences.edit().putFloat(key, value).commit()
            }
            is Int -> {
                sharedPreferences.edit().putInt(key, value).commit()
            }
            is Long -> {
                sharedPreferences.edit().putLong(key, value).commit()
            }
            is String -> {
                sharedPreferences.edit().putString(key, value).commit()
            }
        }
    }

    fun <T> get(context: Context, key: String, defaultValue: T): T? {
        val sharedPreferences =
            context.getSharedPreferences(Config.CONFIG_NAME, Context.MODE_PRIVATE)
        when (defaultValue) {
            is Boolean -> {
                return sharedPreferences.getBoolean(key, defaultValue) as T
            }
            is Float -> {
                return sharedPreferences.getFloat(key, defaultValue) as T
            }
            is Int -> {
                return sharedPreferences.getInt(key, defaultValue) as T
            }
            is Long -> {
                return sharedPreferences.getLong(key, defaultValue) as T
            }
            is String -> {
                return sharedPreferences.getString(key, defaultValue) as T
            }
            else -> {
                return null as T
            }
        }
    }
}