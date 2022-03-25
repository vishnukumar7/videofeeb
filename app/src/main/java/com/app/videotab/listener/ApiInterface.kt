package com.app.videotab.listener

import com.app.videotab.model.VideoTabResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/users")
    suspend fun getVideoTabPages(@Query("page")page :Int) : Response<VideoTabResponse>
}