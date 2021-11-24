package com.example.tabwithviewpager.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.tabwithviewpager.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    private val tabTitle = intArrayOf(R.string.today, R.string.one_week, R.string.all)

    companion object{
        @JvmStatic
        fun newInstance():HomeFragment{
            return HomeFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = view.findViewById(R.id.notification_tab)
        viewPager = view.findViewById(R.id.notification_view_pager)

        viewPager.apply {
            offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
            adapter = object : FragmentStateAdapter(childFragmentManager, lifecycle) {
                override fun getItemCount(): Int {
                    return 3
                }

                override fun createFragment(position: Int): Fragment {
                    val fragment = MessageFragment()
                    fragment.arguments = Bundle().apply {
                        putInt("type", position)
                    }
                    return fragment
                }
            }
        }
        TabLayoutMediator(
            tabLayout, viewPager, true, true
        ) { tab: TabLayout.Tab, position: Int ->
            tab.setText(tabTitle[position])
        }.attach()
    }

}