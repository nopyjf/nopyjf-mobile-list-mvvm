package com.example.nopyjf.nopyjfmobilelistmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nopyjf.nopyjfmobilelistmvvm.R
import com.example.nopyjf.nopyjfmobilelistmvvm.databinding.ItemFavoriteListBinding
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay

class FavoriteListAdapter(
    private val deleteItem: (data: MobileDisplay) -> Unit
) : ListAdapter<MobileDisplay, FavoriteListAdapter.ViewHolder>(DiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_favorite_list, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), deleteItem)
    }

    class ViewHolder(
        private val binding: ItemFavoriteListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            model: MobileDisplay,
            deleteItem: (data: MobileDisplay) -> Unit
        ) {
            binding.apply {
                this.model = model
                this.favoriteItemImage.load(model.thumbImageURL)
                itemView.setOnClickListener { deleteItem(model) }
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