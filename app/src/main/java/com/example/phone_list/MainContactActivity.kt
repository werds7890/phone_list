package com.example.phone_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.phone_list.databinding.ActivityMainContactBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.util.jar.Attributes.Name

class MainContactActivity : AppCompatActivity() {
    var datalist = ArrayList<Maindata>{}
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
        binding = MainContactActivity.inflate(layoutInflater)
        setContentView(binding.root)

        var viewPager = binding.viewpager
        var tabLayout = binding.tabLayout

        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabLayout,viewPager){
            tab, position -> tab.text = tabTitleArray[position]
            tab.icon = getDrawable(tabIconArray[position])
        }.attach()

        val dataList = mutableListOf<Maindata>()
        dataList.add(Maindata(profileImage = "sn", aname = "써니"))
        dataList.add(Maindata(profileImage = "hy", aname = "효연"))
        dataList.add(Maindata(profileImage = "ty", aname = "태연"))
        dataList.add(Maindata(profileImage = "yr", aname = "유리"))
        dataList.add(Maindata(profileImage = "js", aname = "제시카"))
        dataList.add(Maindata(profileImage = "tp", aname = "티파니"))
        dataList.add(Maindata(profileImage = "sh", aname = "서현"))
        dataList.add(Maindata(profileImage = "ya", aname = "윤아"))
        dataList.add(Maindata(profileImage = "sy", aname = "수영"))




       
        binding.listView.adapter = MainAdapter(this, dataList)

       
        binding.listView.setOnItemClickListener{ parent, view, position, id ->
            val name: String = (binding.listView.adapter.getItem(position) as MyItem).aName
            Toast.makeText(this," $name 선택!", Toast.LENGTH_SHORT).show()


        }
    }
}