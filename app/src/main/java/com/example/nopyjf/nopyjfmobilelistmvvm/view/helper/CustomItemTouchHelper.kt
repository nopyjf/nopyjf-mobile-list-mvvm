package com.example.nopyjf.nopyjfmobilelistmvvm.view.helper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class CustomItemTouchHelper(
    dragDirs: Int,
    swiperDirs: Int,
    private var listener: Listener
) : ItemTouchHelper.SimpleCallback(dragDirs, swiperDirs) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        when (direction) {
            ItemTouchHelper.LEFT -> {
                listener.swipeLeft(position = viewHolder.adapterPosition)
            }
        }
    }

    interface Listener {
        fun swipeLeft(position: Int)
    }
}