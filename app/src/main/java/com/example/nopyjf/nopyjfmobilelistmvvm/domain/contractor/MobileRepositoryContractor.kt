package com.example.nopyjf.nopyjfmobilelistmvvm.domain.contractor

import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Mobile

interface MobileRepositoryContractor {
    suspend fun getMobileList(): List<Mobile>
}