package com.example.nopyjf.nopyjfmobilelistmvvm.data.repository

import com.example.nopyjf.nopyjfmobilelistmvvm.data.api.MobileApi
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.contractor.MobileRepositoryContractor
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.*
import org.koin.dsl.module

val getMobileRepositoryModule = module { single { MobileRepository(get()) } }

class MobileRepository(
    private val api: MobileApi
) : MobileRepositoryContractor {
    override suspend fun getMobileList(): List<Mobile> {
        return api.getMobileList().map { it.toMobile() }
    }

    override suspend fun getMobileImageList(id: Int): List<MobileImage> {
        return api.getMobileImageList(id).map { it.toMobileImage() }
    }
}