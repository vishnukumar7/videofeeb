package com.app.videotab.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.videotab.MainActivity
import com.app.videotab.MyApplication
import com.app.videotab.R
import com.app.videotab.adapter.FeedAdapter
import com.app.videotab.databinding.FeedListFragmentBinding
import com.app.videotab.helper.RoomCreateDialog
import com.app.videotab.listener.PagerListener
import com.app.videotab.model.Feed
import com.app.videotab.model.FeedDao
import javax.inject.Inject

class FeedTabListFragment: Fragment(), PagerListener {

    private lateinit var  binding: FeedListFragmentBinding
    @Inject lateinit var feedDao: FeedDao
    lateinit var roomCreateDialog: RoomCreateDialog
    lateinit var feedAdapter: FeedAdapter
    val  feedList : ArrayList<Feed> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=DataBindingUtil.inflate(inflater, R.layout.feed_list_fragment,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((activity as MainActivity).application as MyApplication).getNetComponent().inject(this)
        roomCreateDialog= RoomCreateDialog(feedDao)
        feedAdapter=FeedAdapter(feedList,this)
        binding.addbtn.setOnClickListener {
            roomCreateDialog.setFeed(null)
            roomCreateDialog.show(childFragmentManager,"Room")
        }
        binding.recyclerViewFeed.setHasFixedSize(false)
        binding.recyclerViewFeed.layoutManager=LinearLayoutManager(activity)
        binding.recyclerViewFeed.adapter=feedAdapter

        (activity as MainActivity).appViewModel.feedListResponse.observe(viewLifecycleOwner){response->
            response?.let {
                feedList.clear()
                feedList.addAll(it)
                feedAdapter.notifyDataSetChanged()
            }

        }
    }

    override fun setPosition(page: Int) {
        roomCreateDialog.setFeed(feedList[page])
        roomCreateDialog.show(childFragmentManager,"Room")
    }
}