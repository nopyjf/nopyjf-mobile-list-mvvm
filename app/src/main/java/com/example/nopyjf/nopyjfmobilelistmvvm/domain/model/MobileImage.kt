package com.example.nopyjf.nopyjfmobilelistmvvm.domain.model

import android.os.Parcelable
import com.example.nopyjf.nopyjfmobilelistmvvm.data.model.MobileImageEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class MobileImage(
    val id: String?,
    val url: String?,
    val mobileId: String?
) : Parcelable

fun MobileImageEntity.toMobileImage(): MobileImage {
    return MobileImage(
        id = this.id,
        url = this.url,
        mobileId = this.mobileId
    )
}