package com.app.videotab.helper

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.app.videotab.R
import com.app.videotab.databinding.CreateRoomDialogBinding
import com.app.videotab.model.Feed
import com.app.videotab.model.FeedDao
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class RoomCreateDialog(private var feedDao: FeedDao) : BottomSheetDialogFragment() {

    lateinit var binding: CreateRoomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.create_room_dialog, container, false)
        return binding.root
    }

    private var feed: Feed? = null

    fun setFeed(feed: Feed?) {
        this.feed = feed
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(feed!=null){
            binding.roomName.isEnabled = false
            binding.checkLive.isChecked = feed!!.live
            binding.roomName.setText(feed!!.roomName)
        }else{
           binding.roomName.setText("")
        }
        binding.createBtn.setOnClickListener {
            if (TextUtils.isEmpty(binding.roomName.text)) {
                Toast.makeText(context, getString(R.string.name_required), Toast.LENGTH_LONG)
                    .show()
            } else {
                if (feed == null) {
                    if (feedDao.getName(binding.roomName.text.toString()).isNotEmpty()) {
                        Toast.makeText(
                            context,
                            getString(R.string.room_already),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val feed = Feed(
                            binding.roomName.text.toString(),
                            binding.checkLive.isChecked,
                            Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis
                        )
                        feedDao.insert(feed)
                        Toast.makeText(
                            context,
                            getString(R.string.room_created),
                            Toast.LENGTH_SHORT
                        ).show()
                        dismiss()
                    }
                } else {
                    feed?.let {
                        it.live=binding.checkLive.isChecked
                        it.dateTime=Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis
                        feedDao.update(it)
                        Toast.makeText(context, getString(R.string.room_updated), Toast.LENGTH_SHORT)
                            .show()
                        dismiss()
                    }

                }

            }
        }

        binding.cancelBtn.setOnClickListener {
            dismiss()
        }

    }
}