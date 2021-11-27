package com.example.nopyjf.nopyjfmobilelistmvvm.data.model

import android.os.Parcelable
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Mobile
import kotlinx.parcelize.Parcelize

@Parcelize
data class MobileEntity(
    val id: String?,
    val price: Double?,
    val rating: Double?,
    val thumbImageURL: String?,
    val description: String?,
    val brand: String?,
    val name: String?
) : Parcelable