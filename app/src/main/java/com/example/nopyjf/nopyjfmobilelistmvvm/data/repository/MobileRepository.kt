package com.example.nopyjf.nopyjfmobilelistmvvm.data.repository

import com.example.nopyjf.nopyjfmobilelistmvvm.data.api.MobileApi
import com.example.nopyjf.nopyjfmobilelistmvvm.data.database.MobileDatabase
import com.example.nopyjf.nopyjfmobilelistmvvm.data.model.toFavorite
import com.example.nopyjf.nopyjfmobilelistmvvm.data.model.toFavoriteEntity
import com.example.nopyjf.nopyjfmobilelistmvvm.data.model.toMobile
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.contractor.MobileRepositoryContractor
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.*
import org.koin.dsl.module

val getMobileRepositoryModule = module { single { MobileRepository(get(), get()) } }

class MobileRepository(
    private val api: MobileApi,
    private val database: MobileDatabase
) : MobileRepositoryContractor {
    override suspend fun getMobileList(): List<Mobile> {
        return api.getMobileList().map { it.toMobile() }
    }

    override suspend fun getMobileImageList(id: Int): List<MobileImage> {
        return api.getMobileImageList(id).map { it.toMobileImage() }
    }

    override suspend fun getFavorites(): List<Favorite> {
        return database
            .favoriteDao()
            .getFavorites()
            .map { it.toFavorite() }
    }

    override suspend fun insertFavorite(data: Mobile) {
        return database.favoriteDao().insertFavorite(data.toFavoriteEntity())
    }

    override suspend fun deleteFavorite(data: Mobile) {
        return database.favoriteDao().deleteFavorite(data.toFavoriteEntity())
    }
}