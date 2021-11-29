package com.example.nopyjf.nopyjfmobilelistmvvm.view.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment.MobileListFragment

class MobileListViewPagerAdapter(
    fm: FragmentActivity,
    private val _tabSize: Int
) : FragmentStateAdapter(fm) {

    override fun getItemCount() = _tabSize

    override fun createFragment(position: Int) = MobileListFragment.createFragment(position)
}