package com.app.videotab

import android.app.Application
import com.app.videotab.daggerInjection.ApiComponent
import com.app.videotab.daggerInjection.ApiModule
import com.app.videotab.daggerInjection.AppModule
import com.app.videotab.daggerInjection.DaggerApiComponent
import retrofit2.Retrofit
import javax.inject.Inject

class MyApplication : Application() {

    private lateinit var apiComponent: ApiComponent

    @Inject lateinit var retrofit: Retrofit
    override fun onCreate() {
        super.onCreate()
       apiComponent=DaggerApiComponent.builder()
           .apiModule(ApiModule("https://reqres.in/"))
           .appModule(AppModule(this))
           .build()

        apiComponent.inject(this)
    }

    fun getNetComponent(): ApiComponent{
        return apiComponent
    }


    val appRepository by lazy { AppRepository(retrofit.create(ApiInterface::class.java)) }

}