package com.wangyz.wanandroid.kotlin.viewmodel

import androidx.navigation.Navigation
import com.wangyz.wanandroid.kotlin.App
import com.wangyz.wanandroid.kotlin.Config
import com.wangyz.wanandroid.kotlin.databinding.FragmentLoginBinding
import com.wangyz.wanandroid.kotlin.extLaunch
import com.wangyz.wanandroid.kotlin.model.LoginModel
import com.wangyz.wanandroid.kotlin.utils.SharedPreferencesUtil
import com.wangyz.wanandroid.kotlin.viewmodel.base.BaseViewModel

class LoginViewModel : BaseViewModel<FragmentLoginBinding>() {

    fun login(username: String, password: String) {
        extLaunch({
            LoginModel.login(username, password)
            ViewModelBus.INSTANCE.get(ShareViewModel::class.java)!!.login = true
            SharedPreferencesUtil.put(App.app, Config.CONFIG_KEY_LOGIN, true)
            Navigation.findNavController(binding.root).navigateUp()
        })
    }

}