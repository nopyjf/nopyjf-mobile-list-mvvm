package com.example.nopyjf.nopyjfmobilelistmvvm.domain.model

import android.os.Parcelable
import com.example.nopyjf.nopyjfmobilelistmvvm.data.model.FavoriteEntity
import com.example.nopyjf.nopyjfmobilelistmvvm.data.model.MobileEntity
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mobile(
    val id: Int?,
    val price: Double?,
    val rating: Double?,
    val thumbImageURL: String?,
    val description: String?,
    val brand: String?,
    val name: String?,
    var favorite: Favorite? = null
) : Parcelable