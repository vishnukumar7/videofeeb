package com.app.videotab

class AppRepository(private var apiInterface: ApiInterface) {

    suspend fun getVideoTabList(page: Int) =apiInterface.getVideoTabPages(page)
}