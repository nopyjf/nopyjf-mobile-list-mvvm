package com.example.nopyjf.nopyjfmobilelistmvvm.domain.contractor

import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Mobile
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.MobileImage

interface MobileRepositoryContractor {
    suspend fun getMobileList(): List<Mobile>
    suspend fun getMobileImageList(): List<MobileImage>
}