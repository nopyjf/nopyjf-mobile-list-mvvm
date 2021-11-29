package com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.nopyjf.nopyjfmobilelistmvvm.R
import com.example.nopyjf.nopyjfmobilelistmvvm.databinding.FragmentMobileListBinding
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.MobileListViewModel
import com.example.nopyjf.nopyjfmobilelistmvvm.view.adapter.MobileListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MobileListFragment : Fragment() {

    private lateinit var _binding: FragmentMobileListBinding
    private lateinit var _adapter: MobileListAdapter

    private var _page: Int = INDEX_DEFAULT

    private val _viewModel: MobileListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _page = arguments?.getInt(PAGE_EXTRA) ?: INDEX_DEFAULT
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
        _viewModel.mobileList.observe(requireActivity()) { _adapter.submitList(it) }
        _viewModel.state.observe(requireActivity()) { _binding.state = it }
        _viewModel.errorMessage.observe(requireActivity()) { _binding.errorMessage = it }
    }

    private fun setRecyclerView() {
        _adapter = MobileListAdapter(::onClickItem)
        _binding.mobileListRecyclerView.adapter = _adapter
    }

    private fun onClickItem(id: String) {
        // do nothing
    }

    companion object {
        private const val INDEX_DEFAULT = -1
        private const val PAGE_EXTRA = "PAGE_EXTRA"

        fun createFragment(page: Int): MobileListFragment {
            return MobileListFragment().apply {
                arguments = Bundle().apply {
                    putInt(PAGE_EXTRA, page)
                }
            }
        }
    }
}