package com.wangyz.wanandroid.kotlin.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wangyz.wanandroid.kotlin.BR
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.bean.ProjectDetailResponse
import com.wangyz.wanandroid.kotlin.databinding.ItemProjectDetailBinding
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus

class ProjectDetailAdapter :
    PagedListAdapter<ProjectDetailResponse.DataBean, ProjectDetailAdapter.RecyclerViewHolder>(
        (DIFF_STUDENT)
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project_detail, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.binding?.setVariable(BR.item, item)
        }
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var binding: ItemProjectDetailBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)!!
            binding!!.setVariable(BR.click, ClickProxy())
        }
    }

    companion object {

        private val DIFF_STUDENT =
            object : DiffUtil.ItemCallback<ProjectDetailResponse.DataBean>() {
                override fun areItemsTheSame(
                    oldItem: ProjectDetailResponse.DataBean,
                    newItem: ProjectDetailResponse.DataBean
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: ProjectDetailResponse.DataBean,
                    newItem: ProjectDetailResponse.DataBean
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    class ClickProxy {
        fun openArticle(view: View, article: ProjectDetailResponse.DataBean) {
            val bundle = Bundle()
            bundle.putString("title", article.title)
            bundle.putString("url", article.link)
            Navigation.findNavController(view).navigate(R.id.articleFragment, bundle)
            ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
                View.GONE
        }
    }
}