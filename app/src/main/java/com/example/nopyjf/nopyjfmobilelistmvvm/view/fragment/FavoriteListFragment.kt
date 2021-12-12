package com.example.nopyjf.nopyjfmobilelistmvvm.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.nopyjf.nopyjfmobilelistmvvm.R
import com.example.nopyjf.nopyjfmobilelistmvvm.databinding.FragmentFavoriteListBinding
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.FavoriteListViewModel
import com.example.nopyjf.nopyjfmobilelistmvvm.view.adapter.FavoriteListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteListFragment : Fragment(), MobileListViewPagerFragment.Listener {

    private lateinit var _binding: FragmentFavoriteListBinding
    private lateinit var _adapter: FavoriteListAdapter

    private val _viewModel: FavoriteListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorite_list,
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
        _viewModel.getFavoriteList()
    }

    private fun setRecyclerView() {
        _adapter = FavoriteListAdapter(::deleteItem)
        _binding.favoriteListRecyclerView.adapter = _adapter
    }

    private fun observeLiveData() {
        _viewModel.apply {
            state.observe(viewLifecycleOwner) {
                _binding.model = it
                _adapter.submitList(it.data)
            }
        }
    }

    private fun deleteItem(data: MobileDisplay) {
        _viewModel.apply {
            deleteFavorite(data)
            getFavoriteList()
        }
    }

    companion object {
        fun createFragment(): FavoriteListFragment = FavoriteListFragment()
    }
}