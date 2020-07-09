package com.wangyz.wanandroid.kotlin.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.viewmodel.MainViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ShareViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel =
            ViewModelBus.INSTANCE.provide(this, MainViewModel::class.java)
        viewModel.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.binding.data = ViewModelBus.INSTANCE.provide(this, ShareViewModel::class.java)
        viewModel.binding.lifecycleOwner = this
    }

    override fun onDestroy() {
        super.onDestroy()
        ViewModelBus.INSTANCE.release()
    }

    @SuppressLint("WrongConstant")
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val drawer = ViewModelBus.INSTANCE.get(MainViewModel::class.java)!!.binding.mainDrawer
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawers()
            return true
        }
        val controller = Navigation.findNavController(this, R.id.content_fragment_host)
        return if (controller.navigateUp()) {
            true
        } else super.onKeyDown(keyCode, event)
    }
}
