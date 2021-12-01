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
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.MobileListViewModel
import com.example.nopyjf.nopyjfmobilelistmvvm.view.ID_KEY
import com.example.nopyjf.nopyjfmobilelistmvvm.view.MOBILE_LIST_INDEX
import com.example.nopyjf.nopyjfmobilelistmvvm.view.PAGE_EXTRA
import com.example.nopyjf.nopyjfmobilelistmvvm.view.adapter.MobileListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MobileListFragment : Fragment() {

    private lateinit var _binding: FragmentMobileListBinding
    private lateinit var _adapter: MobileListAdapter

    private var _page: Int = MOBILE_LIST_INDEX

    private val _viewModel: MobileListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _page = arguments?.getInt(PAGE_EXTRA) ?: MOBILE_LIST_INDEX
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
        _viewModel.state.observe(viewLifecycleOwner) { _binding.state = it }
        _viewModel.mobileList.observe(viewLifecycleOwner) { _adapter.submitList(it) }
        _viewModel.errorMessage.observe(viewLifecycleOwner) { _binding.errorMessage = it }
    }

    private fun setRecyclerView() {
        _adapter = MobileListAdapter(::onClickItem)
        _binding.mobileListRecyclerView.adapter = _adapter
    }

    private fun onClickItem(id: Int) {
        val bundle = bundleOf(ID_KEY to id)
        findNavController().navigate(
            R.id.action_mobileListViewPagerFragment_to_mobileDetailActivity,
            bundle
        )
    }

    companion object {
        fun createFragment(page: Int): MobileListFragment {
            return MobileListFragment().apply {
                arguments = Bundle().apply {
                    putInt(PAGE_EXTRA, page)
                }
            }
        }
    }
}