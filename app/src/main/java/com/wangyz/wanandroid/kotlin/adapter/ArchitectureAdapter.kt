package com.wangyz.wanandroid.kotlin.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.wangyz.wanandroid.kotlin.BR
import com.wangyz.wanandroid.kotlin.R
import com.wangyz.wanandroid.kotlin.bean.ArchitectureResponse
import com.wangyz.wanandroid.kotlin.databinding.ItemArchitectureBinding
import com.wangyz.wanandroid.kotlin.viewmodel.ArchitectureViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.BottomViewModel
import com.wangyz.wanandroid.kotlin.viewmodel.ViewModelBus
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter


class ArchitectureAdapter(val context: Context?) :
    RecyclerView.Adapter<ArchitectureAdapter.RecyclerViewHolder>() {

    val data = ViewModelBus.INSTANCE.get(ArchitectureViewModel::class.java)?.data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_architecture, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = data?.value?.size!!

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val bean = data?.value?.get(position)
        holder.binding?.setVariable(BR.item, bean)
        holder.binding?.tags?.adapter = object :
            TagAdapter<ArchitectureResponse.Children>(bean?.children) {
            override fun getView(
                parent: FlowLayout?,
                position: Int,
                t: ArchitectureResponse.Children?
            ): View {
                val tv = LayoutInflater.from(context).inflate(
                    R.layout.item_architecture_tag,
                    holder.binding!!.tags,
                    false
                ) as TextView
                tv.text = t?.name
                return tv
            }
        }
        holder.binding?.tags?.setOnTagClickListener { view, position, parent ->
            val bean = bean?.children?.get(position)
            val bundle = Bundle()
            bundle.putInt("cid", bean?.id!!)
            bundle.putString("name", bean?.name)
            Navigation.findNavController(view).navigate(R.id.architectureDetailFragment, bundle)
            ViewModelBus.INSTANCE.get(BottomViewModel::class.java)?.binding?.root?.visibility =
                View.GONE
            true
        }
    }


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var binding: ItemArchitectureBinding? = null

        init {
            binding = DataBindingUtil.bind(itemView)!!
        }
    }
}