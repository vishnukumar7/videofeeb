package com.app.videotab.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.videotab.listener.PagerListener
import com.app.videotab.R
import com.app.videotab.databinding.PagerListBinding

class PagerAdapter(private val context: Context,private val pagerListener: PagerListener) :
    RecyclerView.Adapter<PagerAdapter.PagerViewHolder>() {
    private var totalNo: Int = 0
    private var currentPage = 1

    fun setTotalNoPager(total: Int, currentPage: Int) {
        this.totalNo = total
        this.currentPage = currentPage
        notifyDataSetChanged()
    }


    class PagerViewHolder(binding: PagerListBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val itemBinding = DataBindingUtil.inflate<PagerListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.pager_list, parent, false
        )
        return PagerViewHolder(itemBinding)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.itemBinding.pageNo.text = "${position + 1}"
        if (currentPage != position + 1) {
            holder.itemBinding.mainLay.setOnClickListener {
                pagerListener.setPosition(position + 1)
            }
            holder.itemBinding.mainLay.setBackgroundColor(context.getColor(R.color.black))
            holder.itemBinding.pageNo.setTextColor(context.getColor(R.color.white))

        }else{
            holder.itemBinding.mainLay.background=context.getDrawable(R.drawable.cornor_storke)
            holder.itemBinding.pageNo.setTextColor(context.getColor(R.color.black))
        }
    }

    override fun getItemCount(): Int {
        return totalNo
    }


}