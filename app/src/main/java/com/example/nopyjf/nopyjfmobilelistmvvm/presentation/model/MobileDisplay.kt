package com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model

import android.os.Parcelable
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Favorite
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Mobile
import kotlinx.parcelize.Parcelize

@Parcelize
data class MobileDisplay(
    val id: Int?,
    val price: Double?,
    val rating: Double?,
    val thumbImageURL: String?,
    val description: String?,
    val brand: String?,
    val name: String?,
    var favorite: Boolean?
) : Parcelable

fun Mobile.toMobileDisplay(): MobileDisplay {
    return MobileDisplay(
        id = this.id,
        price = this.price,
        rating = this.rating,
        thumbImageURL = this.thumbImageURL,
        description = this.description,
        brand = this.brand,
        name = this.name,
        favorite = null
    )
}

fun MobileDisplay.toMobile(): Mobile {
    return Mobile(
        id = this.id,
        price = this.price,
        rating = this.rating,
        thumbImageURL = this.thumbImageURL,
        description = this.description,
        brand = this.brand,
        name = this.name
    )
}