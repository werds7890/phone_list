package com.example.phone_list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.phone_list.databinding.ActivityMainContactBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainContactActivity : AppCompatActivity(),ShowDialogFragment.DialogListener {
    private val binding by lazy { ActivityMainContactBinding.inflate(layoutInflater) }

    private val tabTitleArray = arrayOf(
        "연락처", "마이페이지"
    )
    private val tabIconArray = arrayOf(
        R.drawable.bottom_nav_contact_icon_24,
        R.drawable.bottom_nav_mypage_icon_24
    )

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.contact_Main,fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        Toast.makeText(dialog.context,"저장 됨", Toast.LENGTH_SHORT).show()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        Toast.makeText(dialog.context,"취소 됨", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var viewPager = binding.viewpager
        var tabLayout = binding.tabLayout

        viewPager.adapter = ViewPagerAdapter(this)


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
            tab.icon = getDrawable(tabIconArray[position])
        }.attach()
    }
}
