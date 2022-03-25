package com.app.videotab

import androidx.lifecycle.*
import com.app.videotab.model.Feed
import com.app.videotab.model.VideoTabResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel(private val appRepository: AppRepository): ViewModel() {

    val videoTabResponse: MutableLiveData<VideoTabResponse> = MutableLiveData()

    val feedListResponse: LiveData<MutableList<Feed>> =appRepository.feedList.asLiveData()

    fun getVideoTabResponse(page: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val response=appRepository.getVideoTabList(page)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    videoTabResponse.value=response.body()
                }
            }
        }
    }

    class AppViewModelFactory(private val appRepository: AppRepository) :
        ViewModelProvider.Factory {

        /**
         * Creates a new instance of the given `Class`.
         *
         * @param modelClass a `Class` whose instance is requested
         * @return a newly created ViewModel
         */
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AppViewModel(appRepository) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }
    }
}