package com.app.videotab

import androidx.annotation.WorkerThread
import com.app.videotab.listener.ApiInterface
import com.app.videotab.model.Feed
import com.app.videotab.model.FeedDao
import kotlinx.coroutines.flow.Flow

class AppRepository(private var apiInterface: ApiInterface,private val feedDao: FeedDao) {

    suspend fun getVideoTabList(page: Int) =apiInterface.getVideoTabPages(page)

    val feedList : Flow<MutableList<Feed>> =feedDao.getAll()
}