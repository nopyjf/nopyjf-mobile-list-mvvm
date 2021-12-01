package com.example.nopyjf.nopyjfmobilelistmvvm.data.service

import com.example.nopyjf.nopyjfmobilelistmvvm.data.model.MobileEntity
import com.example.nopyjf.nopyjfmobilelistmvvm.data.model.MobileImageEntity
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

val getMobileServiceModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MobileService::class.java)
    }
}

interface MobileService {
    @GET("/v3/183cc4b1-1e33-4094-961b-9d4f924843e7")
    suspend fun getMobileList(): List<MobileEntity>

    @GET("/v3/cfc4f42d-77cb-41d7-8832-3a94b63a1afa/{id}")
    suspend fun getMobileImageList(@Path("id") id: Int): List<MobileImageEntity>
}