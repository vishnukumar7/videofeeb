package com.app.videotab.daggerInjection

import com.app.videotab.MainActivity
import com.app.videotab.MyApplication
import com.app.videotab.ui.FeedTabListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,ApiModule::class])
interface ApiComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(application: MyApplication)

    fun inject(feedTabListFragment: FeedTabListFragment)
}