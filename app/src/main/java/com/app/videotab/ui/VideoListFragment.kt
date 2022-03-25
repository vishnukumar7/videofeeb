package com.app.videotab.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.videotab.MainActivity
import com.app.videotab.listener.PagerListener
import com.app.videotab.R
import com.app.videotab.adapter.PagerAdapter
import com.app.videotab.adapter.VideoListAdapter
import com.app.videotab.databinding.VideoListFragmentBinding
import com.app.videotab.model.DataItem

class VideoListFragment : Fragment(), PagerListener {

    private lateinit var binding: VideoListFragmentBinding
    private val dataItemList: ArrayList<DataItem> = ArrayList()
    private lateinit var videoListAdapter: VideoListAdapter
    private lateinit var progressDialog: ProgressDialog
    private var totalNoOfPages = 0
    private var currentPage = 1
    private lateinit var pagerAdapter: PagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.video_list_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoListAdapter=VideoListAdapter(requireContext(), dataItemList)
        pagerAdapter= PagerAdapter(requireContext(),this)
        binding.recyclerView.setHasFixedSize(false)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = videoListAdapter
        progressDialog = ProgressDialog(activity)
        progressDialog.setMessage("Loading..Please Wait...")
        videoListPage()
        binding.recyclerViewPager.setHasFixedSize(false)
        binding.recyclerViewPager.layoutManager=LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
binding.recyclerViewPager.adapter=pagerAdapter

    }

    fun videoListPage() {
        if (!progressDialog.isShowing)
            progressDialog.show()
        (activity as MainActivity).appViewModel.getVideoTabResponse(currentPage)

        (activity as MainActivity).appViewModel.videoTabResponse.observe(viewLifecycleOwner) { response ->
            response?.let {
                if (progressDialog.isShowing)
                    progressDialog.dismiss()
                dataItemList.clear()
                totalNoOfPages = it.totalPages
                currentPage = it.page
                pagerAdapter.setTotalNoPager(totalNoOfPages,currentPage)
                pagerAdapter.notifyDataSetChanged()
                dataItemList.addAll(it.data)
                videoListAdapter.notifyDataSetChanged()
            }

        }
    }

    override fun setPosition(page: Int) {
        currentPage=page
        videoListPage()
    }


}