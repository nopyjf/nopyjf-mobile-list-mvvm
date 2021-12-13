package com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.nopyjf.nopyjfmobilelistmvvm.R
import com.example.nopyjf.nopyjfmobilelistmvvm.databinding.FragmentMobileListViewPagerBinding
import com.example.nopyjf.nopyjfmobilelistmvvm.view.adapter.MobileListViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MobileListViewPagerFragment : Fragment() {

    private lateinit var _binding: FragmentMobileListViewPagerBinding
    private lateinit var _adapter: MobileListViewPagerAdapter

    private val _tabTitles = listOf(
        R.string.app_mobile_list_tab_title,
        R.string.app_favorite_list_tab_title
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_mobile_list_view_pager,
            container,
            false
        )
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
        setTabLayout()
    }

    private fun setViewPager() {
        _binding.apply {
            _adapter = MobileListViewPagerAdapter(requireActivity(), _tabTitles.size)
            mobileListViewPager.adapter = _adapter
        }
    }

    private fun setTabLayout() {
        _binding.apply {
            mobileListTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> _adapter.getMobileListListener()?.reload()
                        1 -> _adapter.getFavoriteListener()?.reload()
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    // do nothing
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> _adapter.getMobileListListener()?.reload()
                        1 -> _adapter.getFavoriteListener()?.reload()
                    }
                }
            })
            TabLayoutMediator(mobileListTabLayout, mobileListViewPager) { tab, position ->
                tab.text = getString(_tabTitles[position])
            }.attach()
        }
    }

    interface Listener {
        fun reload()
    }
}