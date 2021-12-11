package com.example.nopyjf.nopyjfmobilelistmvvm.data.model

import android.os.Parcelable
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Mobile
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MobileEntity(
    @SerializedName("id") val id: Int?,
    @SerializedName("price") val price: Double?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("thumbImageURL") val thumbImageURL: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("brand") val brand: String?,
    @SerializedName("name") val name: String?
) : Parcelable

fun MobileEntity.toMobile(): Mobile {
    return Mobile(
        id = this.id,
        price = this.price,
        rating = this.rating,
        thumbImageURL = this.thumbImageURL,
        description = this.description,
        brand = this.brand,
        name = this.name,
    )
}