package com.app.videotab.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.videotab.R
import com.app.videotab.databinding.VideoListBinding
import com.app.videotab.model.DataItem
import com.squareup.picasso.Picasso


class VideoListAdapter(
    private val context: Context,
    private val dataItemList: ArrayList<DataItem>
) : RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder>() {

    class VideoListViewHolder(binding: VideoListBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListViewHolder {
        val itemBinding = DataBindingUtil.inflate<VideoListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.video_list,
            parent,
            false
        )
        return VideoListViewHolder(itemBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VideoListViewHolder, position: Int) {
        val dataItem=dataItemList[position]
        Picasso.get().load(dataItem.avatar).into(holder.itemBinding.profileImage)
      holder.itemBinding.name.text="${dataItem.firstName} ${dataItem.lastName}"
        holder.itemBinding.email.text= dataItem.email
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }
}