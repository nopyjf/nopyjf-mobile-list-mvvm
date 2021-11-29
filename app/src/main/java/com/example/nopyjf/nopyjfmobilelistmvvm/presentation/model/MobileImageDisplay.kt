package com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MobileImageDisplay(
    val id: String?,
    val url: String?,
    val mobileId: String?
) : Parcelable