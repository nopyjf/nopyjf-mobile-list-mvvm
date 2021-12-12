package com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nopyjf.nopyjfmobilelistmvvm.R
import com.example.nopyjf.nopyjfmobilelistmvvm.databinding.FragmentMobileListBinding
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.MobileListViewModel
import com.example.nopyjf.nopyjfmobilelistmvvm.view.adapter.MobileListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MobileListFragment : Fragment(), MobileListViewPagerFragment.Listener {

    private lateinit var _binding: FragmentMobileListBinding
    private lateinit var _adapter: MobileListAdapter

    private val _viewModel: MobileListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_mobile_list,
            container,
            false
        )
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        observeLiveData()
    }

    override fun reload() {
        _viewModel.getMobileList()
    }

    private fun setRecyclerView() {
        _adapter = MobileListAdapter(::onClickItem, ::onFavoriteItem, ::onUnFavoriteItem)
        _binding.mobileListRecyclerView.adapter = _adapter
    }

    private fun observeLiveData() {
        _viewModel.apply {
            state.observe(viewLifecycleOwner) {
                _binding.model = it
                _adapter.submitList(it.data)
            }
        }
    }

    private fun onClickItem(data: MobileDisplay) {
        bundleOf(
            DATA_EXTRA to data
        ).let {
            findNavController().navigate(
                R.id.action_mobileListViewPagerFragment_to_mobileDetailActivity,
                it
            )
        }
    }

    private fun onFavoriteItem(data: MobileDisplay) {
        _viewModel.apply {
            insertFavorite(data)
            getMobileList()
        }
    }

    private fun onUnFavoriteItem(data: MobileDisplay) {
        _viewModel.apply {
            deleteFavorite(data)
            getMobileList()
        }
    }

    companion object {
        private const val DATA_EXTRA = "DATA_EXTRA"

        fun createFragment() = MobileListFragment()
    }
}