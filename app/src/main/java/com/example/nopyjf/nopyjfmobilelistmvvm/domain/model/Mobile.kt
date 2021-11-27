package com.example.nopyjf.nopyjfmobilelistmvvm.domain.model

import android.os.Parcelable
import com.example.nopyjf.nopyjfmobilelistmvvm.data.model.MobileEntity
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mobile(
    val id: String?,
    val price: Double?,
    val rating: Double?,
    val thumbImageURL: String?,
    val description: String?,
    val brand: String?,
    val name: String?
) : Parcelable

fun MobileEntity.toMobile(): Mobile {
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

fun Mobile.toMobileDisplay(): MobileDisplay {
    return MobileDisplay(
        id = this.id,
        price = this.price,
        rating = this.rating,
        thumbImageURL = this.thumbImageURL,
        description = this.description,
        brand = this.brand,
        name = this.name
    )
}