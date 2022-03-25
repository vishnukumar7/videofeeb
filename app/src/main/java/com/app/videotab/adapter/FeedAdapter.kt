package com.app.videotab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.videotab.R
import com.app.videotab.databinding.FeedListBinding
import com.app.videotab.listener.PagerListener
import com.app.videotab.model.Feed
import java.text.SimpleDateFormat
import java.util.*

class FeedAdapter(val feedList: ArrayList<Feed>,private val pagerListener: PagerListener) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    class FeedViewHolder(val feedListBinding: FeedListBinding) :
        RecyclerView.ViewHolder(feedListBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val feedListBinding = DataBindingUtil.inflate<FeedListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.feed_list, parent, false
        )
        return FeedViewHolder(feedListBinding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.feedListBinding.title.text = feedList[position].roomName
        holder.feedListBinding.live.visibility=if(feedList[position].live) View.VISIBLE else View.GONE
        val utc=Date(feedList[position].dateTime)
        val df = SimpleDateFormat("MMM dd, yyyy HH:mm:ss a", Locale.ENGLISH)
        df.timeZone= TimeZone.getDefault()
        holder.feedListBinding.createTime.text=df.format(utc)
        holder.feedListBinding.mainLay.setOnClickListener { pagerListener.setPosition(position) }
    }

    override fun getItemCount(): Int {
        return feedList.size
    }
}