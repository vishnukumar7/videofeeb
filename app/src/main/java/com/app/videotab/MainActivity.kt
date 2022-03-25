package com.app.videotab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.app.videotab.databinding.ActivityMainBinding
import com.app.videotab.ui.FeedTabListFragment
import com.app.videotab.ui.VideoListFragment
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var retrofit: Retrofit
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        (application as MyApplication).getNetComponent().inject(this)
        val videoPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        videoPagerAdapter.addFragment(VideoListFragment(),"Videos")
        videoPagerAdapter.addFragment(FeedTabListFragment(),"Feeds")
        binding.viewPager.adapter=videoPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

    }
    val appViewModel: AppViewModel by viewModels {
        AppViewModel.AppViewModelFactory((application as MyApplication).appRepository)
    }


    inner class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val fragmentList: ArrayList<Fragment> = ArrayList()
        private val titleList: ArrayList<String> = ArrayList()

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment,title: String){
            fragmentList.add(fragment)
            titleList.add(title)
        }


        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }


        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }
    }
}