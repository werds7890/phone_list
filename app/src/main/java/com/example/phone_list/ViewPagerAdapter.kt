package com.example.phone_list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


private const val FRAG_NUMS = 2

class ViewPagerAdapter(fragmentActivity:FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return FRAG_NUMS
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> return ContactListFragment()
            else -> return MyPageFragment()
        }
    }
}