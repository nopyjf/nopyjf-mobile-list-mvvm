package com.example.nopyjf.nopyjfmobilelistmvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MobileImageEntity(
    @SerializedName("id") val id: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("mobile_id") val mobileId: String?
) : Parcelable
