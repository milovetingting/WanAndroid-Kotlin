package com.wangyz.wanandroid.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wangyz.wanandroid.kotlin.BR
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.binding.ClickProxy
import com.wangyz.wanandroid.kotlin.databinding.ItemProjectBinding
import com.wangyz.wanandroid.kotlin.viewmodel.ProjectViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus


class ProjectAdapter(val context: Context?) :
    RecyclerView.Adapter<ProjectAdapter.RecyclerViewHolder>() {

    val data = ViewModelBus.INSTANCE.get(ProjectViewModel::class.java)?.data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = data?.value?.size!!

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val bean = data?.value?.get(position)
        holder.binding?.setVariable(BR.item, bean)
    }


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var binding: ItemProjectBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)!!
            binding!!.setVariable(BR.click, ClickProxy())
        }
    }
}