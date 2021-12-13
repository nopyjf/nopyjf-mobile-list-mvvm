package com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.nopyjf.nopyjfmobilelistmvvm.R
import com.example.nopyjf.nopyjfmobilelistmvvm.databinding.FragmentMobileListBinding
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.MobileListViewModel
import com.example.nopyjf.nopyjfmobilelistmvvm.view.adapter.MobileListAdapter
import com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment.MobileListFilterDialogFragment.Companion.MOBILE_LIST_FILTER_EXTRA
import com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment.MobileListFilterDialogFragment.Companion.MOBILE_LIST_FILTER_RESULT_EXTRA
import org.koin.androidx.viewmodel.ext.android.viewModel

class MobileListFragment : Fragment(),
    MobileListViewPagerFragment.Listener {

    private lateinit var _binding: FragmentMobileListBinding
    private lateinit var _adapter: MobileListAdapter

    private val _viewModel: MobileListViewModel by viewModel()
    private var _choice: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        listenFragmentResult()
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_mobile_list_filter, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                MobileListFilterDialogFragment.createDialog(_viewModel.filterChoice.value ?: -1)
                    .show(
                        childFragmentManager,
                        MobileListFilterDialogFragment.MOBILE_LIST_FILTER_DIALOG_FM_TAG
                    )
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
                false
            }
        }
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
            filterChoice.observe(viewLifecycleOwner) {}
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

    private fun listenFragmentResult() {
        setFragmentResultListener(MOBILE_LIST_FILTER_RESULT_EXTRA) { _, bundle ->
            _choice = bundle.getInt(MOBILE_LIST_FILTER_EXTRA)
        }
    }

    companion object {
        private const val DATA_EXTRA = "DATA_EXTRA"

        fun createFragment() = MobileListFragment()
    }
}