package com.robotsoftwarestudio.footballapp.views.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TabPageAdapter(private val fragmentManager: FragmentManager?): FragmentPagerAdapter(fragmentManager) {

    private val fragmentList = arrayListOf<Fragment?>()
    private val tabTitleList = arrayListOf<String?>()

    override fun getItem(position: Int): Fragment? = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? = tabTitleList[position]

    fun setUPTab(fragment: Fragment?, tabTitle: String?) {
        fragmentList.add(fragment)
        tabTitleList.add(tabTitle)
    }

}