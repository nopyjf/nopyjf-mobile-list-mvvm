package com.example.nopyjf.nopyjfmobilelistmvvm.data.model

import android.os.Parcelable
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