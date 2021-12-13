package com.example.nopyjf.nopyjfmobilelistmvvm.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment.FavoriteListFragment
import com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment.MobileListFragment
import com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment.MobileListViewPagerFragment

class MobileListViewPagerAdapter(
    fm: FragmentActivity,
    private val _tabSize: Int
) : FragmentStateAdapter(fm) {

    private var mobileListListener: MobileListViewPagerFragment.Listener? = null
    private var favoriteListener: MobileListViewPagerFragment.Listener? = null

    override fun getItemCount() = _tabSize

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MobileListFragment.createFragment().also { mobileListListener = it }
            1 -> FavoriteListFragment.createFragment().also { favoriteListener = it }
            else -> Fragment()
        }
    }

    fun getMobileListListener(): MobileListViewPagerFragment.Listener? = mobileListListener
    fun getFavoriteListener(): MobileListViewPagerFragment.Listener? = favoriteListener
}