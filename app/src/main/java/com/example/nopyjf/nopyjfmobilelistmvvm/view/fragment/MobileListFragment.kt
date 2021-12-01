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

class MobileListFragment : Fragment() {

    private lateinit var _binding: FragmentMobileListBinding
    private lateinit var _adapter: MobileListAdapter

    private var _page: Int = PAGE_INDEX

    private val _viewModel: MobileListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _page = arguments?.getInt(PAGE_EXTRA) ?: PAGE_INDEX
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

    private fun setRecyclerView() {
        _adapter = MobileListAdapter(::onClickItem)
        _binding.mobileListRecyclerView.adapter = _adapter
    }

    private fun observeLiveData() {
        _viewModel.apply {
            state.observe(viewLifecycleOwner) { _binding.state = it }
            mobileList.observe(viewLifecycleOwner) { _adapter.submitList(it) }
            errorMessage.observe(viewLifecycleOwner) { _binding.errorMessage = it }
        }
    }

    private fun onClickItem(id: Int, data: MobileDisplay) {
        bundleOf(
            ID_EXTRA to id,
            DATA_EXTRA to data
        ).let {
            findNavController().navigate(
                R.id.action_mobileListViewPagerFragment_to_mobileDetailActivity,
                it
            )
        }
    }

    companion object {
        private const val PAGE_INDEX = 0
        private const val PAGE_EXTRA = "PAGE_EXTRA"
        private const val ID_EXTRA = "ID_EXTRA"
        private const val DATA_EXTRA = "DATA_EXTRA"

        fun createFragment(page: Int): MobileListFragment {
            return MobileListFragment().apply {
                arguments = Bundle().apply {
                    putInt(PAGE_EXTRA, page)
                }
            }
        }
    }
}