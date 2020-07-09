package com.wangyz.wanandroid.kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wangyz.wanandroid.kotlin.BR
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.bean.WxDetailResponse
import com.wangyz.wanandroid.kotlin.binding.ClickProxy
import com.wangyz.wanandroid.kotlin.databinding.ItemWxDetailBinding

class WxDetailAdapter :
    PagedListAdapter<WxDetailResponse.DataBean, WxDetailAdapter.RecyclerViewHolder>(
        (DIFF_STUDENT)
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wx_detail, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.binding?.setVariable(BR.item, item)
        }
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var binding: ItemWxDetailBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)!!
            binding!!.setVariable(BR.click, ClickProxy())
        }
    }

    companion object {

        private val DIFF_STUDENT =
            object : DiffUtil.ItemCallback<WxDetailResponse.DataBean>() {
                override fun areItemsTheSame(
                    oldItem: WxDetailResponse.DataBean,
                    newItem: WxDetailResponse.DataBean
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: WxDetailResponse.DataBean,
                    newItem: WxDetailResponse.DataBean
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}