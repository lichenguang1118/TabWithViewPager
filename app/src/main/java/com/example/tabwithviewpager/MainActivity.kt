package com.example.tabwithviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.tabwithviewpager.fragment.*
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private val tabTexts = arrayOf("首页", "导航", "问答", "项目", "我的")
    private val tabDrawable = intArrayOf(
        R.drawable.ic_bottom_bar_home,
        R.drawable.ic_bottom_bar_navigation,
        R.drawable.ic_bottom_bar_faq,
        R.drawable.ic_bottom_bar_system,
        R.drawable.ic_bottom_bar_project
    )

    private val fragments = arrayListOf(
        HomeFragment.newInstance(),
        NavigationFragment.newInstance(),
        QAFragment.newInstance(),
        ProjectFragment.newInstance(),
        UserFragment.newInstance()
    )

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)


        viewPager.offscreenPageLimit = 1
        viewPager.adapter = BaseViewPagerAdapter(supportFragmentManager, fragments)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.customView?.apply {
                    val icon = findViewById<ImageView>(R.id.iv_tab_icon)
                    val text = findViewById<TextView>(R.id.tv_tab_name)
                    icon.setColorFilter(ContextCompat.getColor(icon.context, R.color.app_color))
                    text.setTextColor(ContextCompat.getColor(text.context, R.color.app_color))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView?.apply {
                    val icon = findViewById<ImageView>(R.id.iv_tab_icon)
                    val text = findViewById<TextView>(R.id.tv_tab_name)
                    icon.setColorFilter(ContextCompat.getColor(icon.context, R.color.gray_alpha))
                    text.setTextColor(ContextCompat.getColor(text.context, R.color.black))
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        var currentItem = tabLayout.selectedTabPosition
        if (currentItem == -1) currentItem = 0
        tabLayout.removeAllTabs()
        for (i in fragments.indices) {
            val layoutInflater = LayoutInflater.from(this@MainActivity)
            val tabView: View = layoutInflater.inflate(R.layout.item_tab_main, null)
            val imgTab = tabView.findViewById<ImageView>(R.id.iv_tab_icon)
            val txtTab = tabView.findViewById<TextView>(R.id.tv_tab_name)
            imgTab.setImageDrawable(ContextCompat.getDrawable(imgTab.context, tabDrawable[i]))
            imgTab.setColorFilter(ContextCompat.getColor(imgTab.context, R.color.gray_alpha))
            txtTab.setTextColor(ContextCompat.getColor(txtTab.context, R.color.black))
            txtTab.text = tabTexts[i]
            val tab = tabLayout.newTab()
            tab.customView = tabView
            tabLayout.addTab(tab)
        }
        viewPager.currentItem = currentItem

    }
}