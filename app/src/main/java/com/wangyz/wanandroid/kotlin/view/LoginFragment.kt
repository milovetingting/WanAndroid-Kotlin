package com.wangyz.wanandroid.kotlin.view


import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.databinding.FragmentLoginBinding
import com.wangyz.wanandroid.kotlin.view.base.BaseFragment
import com.wangyz.wanandroid.kotlin.viewmodel.LoginViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus

/**
 * 登录Fragment
 */
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val classT: Class<LoginViewModel>
        get() = LoginViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun initView() {
        val viewModel = ViewModelBus.INSTANCE.get(LoginViewModel::class.java)
        viewModel?.binding?.submit?.setOnClickListener {
            viewModel.login(
                viewModel.binding.username.text.toString(),
                viewModel.binding.password.text.toString()
            )
        }
    }

}
