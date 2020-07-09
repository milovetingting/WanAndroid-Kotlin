package com.wangyz.wanandroid.kotlin.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class BindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun bindImageUrl(view: ImageView, imageUrl: String) {
            val options = RequestOptions()
                .centerCrop()
                .dontAnimate()

            Glide.with(view)
                .load(imageUrl)
                .apply(options)
                .into(view)
        }
    }

}