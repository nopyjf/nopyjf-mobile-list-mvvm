package com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MobileDisplay(
    val id: Int?,
    val price: Double?,
    val rating: Double?,
    val thumbImageURL: String?,
    val description: String?,
    val brand: String?,
    val name: String?
) : Parcelable