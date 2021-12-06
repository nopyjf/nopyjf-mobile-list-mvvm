package com.example.nopyjf.nopyjfmobilelistmvvm.domain.contractor

import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Favorite
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Mobile
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.MobileImage

interface MobileRepositoryContractor {
    suspend fun getMobileList(): List<Mobile>
    suspend fun getMobileImageList(id: Int): List<MobileImage>
    suspend fun getFavorites(): List<Favorite>
    suspend fun insertFavorite(data: Mobile)
    suspend fun deleteFavorite(data: Mobile)
}