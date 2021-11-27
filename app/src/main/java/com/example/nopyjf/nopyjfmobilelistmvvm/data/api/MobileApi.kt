package com.example.nopyjf.nopyjfmobilelistmvvm.data.api

import com.example.nopyjf.nopyjfmobilelistmvvm.data.model.MobileEntity
import com.example.nopyjf.nopyjfmobilelistmvvm.data.service.MobileService
import org.koin.dsl.module

val getMobileApiModule = module { single { MobileApi(get()) } }

class MobileApi(
    private val service: MobileService
) {
    suspend fun getMobileList(): List<MobileEntity> {
        return service.getMobileList()
    }
}