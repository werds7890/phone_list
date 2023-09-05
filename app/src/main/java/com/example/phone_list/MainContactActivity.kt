package com.example.phone_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.phone_list.databinding.ActivityMainContactBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainContactActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainContactBinding.inflate(layoutInflater) }

    private val tabTitleArray = arrayOf(
        "연락처", "마이페이지"
    )
    private val tabIconArray = arrayOf(
        R.drawable.bottom_nav_contact_icon_24,
        R.drawable.bottom_nav_mypage_icon_24
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var viewPager = binding.viewpager
        var tabLayout = binding.tabLayout

        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabLayout,viewPager){
            tab, position -> tab.text = tabTitleArray[position]
            tab.icon = getDrawable(tabIconArray[position])
        }.attach()
    }
}