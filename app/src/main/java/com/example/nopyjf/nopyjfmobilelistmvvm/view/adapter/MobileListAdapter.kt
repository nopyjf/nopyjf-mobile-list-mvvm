package com.example.nopyjf.nopyjfmobilelistmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nopyjf.nopyjfmobilelistmvvm.R
import com.example.nopyjf.nopyjfmobilelistmvvm.databinding.ItemMobileListBinding
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay

class MobileListAdapter(
    private val clickItem: (data: MobileDisplay) -> Unit,
    private val favoriteItem: (data: MobileDisplay) -> Unit,
    private val unFavoriteItem: (data: MobileDisplay) -> Unit,
) : ListAdapter<MobileDisplay, MobileListAdapter.ViewHolder>(DiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_mobile_list, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickItem, favoriteItem, unFavoriteItem)
    }

    class ViewHolder(
        private val binding: ItemMobileListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            model: MobileDisplay,
            clickItem: (data: MobileDisplay) -> Unit,
            favoriteItem: (data: MobileDisplay) -> Unit,
            unFavoriteItem: (data: MobileDisplay) -> Unit,
        ) {
            binding.apply {
                this.model = model
                this.mobileItemImage.load(model.thumbImageURL)
                this.mobileItemFavButton.setOnClickListener { unFavoriteItem(model) }
                this.mobileItemUnFavButton.setOnClickListener { favoriteItem(model) }
                itemView.setOnClickListener { clickItem(model) }
            }
        }
    }

    class DiffUtils : DiffUtil.ItemCallback<MobileDisplay>() {
        override fun areItemsTheSame(oldItem: MobileDisplay, newItem: MobileDisplay): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MobileDisplay, newItem: MobileDisplay): Boolean {
            return oldItem == newItem
        }
    }
}