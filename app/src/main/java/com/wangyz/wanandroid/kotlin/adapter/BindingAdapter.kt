package com.wangyz.wanandroid.kotlin.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wangyz.wanandroid.kotlin.R


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

        @JvmStatic
        @BindingAdapter("icon")
        fun bindIcon(view: TextView, hasAuthor: Boolean) {
            if (hasAuthor) {
                val drawableLeft = view.context.resources.getDrawable(
                    R.drawable.author
                )
                view.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null)
                view.compoundDrawablePadding = 10
            } else {
                val drawableLeft = view.context.resources.getDrawable(
                    R.drawable.share
                )
                view.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null)
                view.compoundDrawablePadding = 10
            }
        }
    }

}