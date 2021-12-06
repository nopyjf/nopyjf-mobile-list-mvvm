package com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase

import com.example.nopyjf.nopyjfmobilelistmvvm.data.repository.MobileRepository
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Mobile
import kotlinx.coroutines.flow.Flow
import org.koin.dsl.module

val getDeleteFavoriteUseCase = module { single { DeleteFavoriteUseCase(get()) } }

class DeleteFavoriteUseCase(
    private val repository: MobileRepository
) {
    suspend operator fun invoke(data: Mobile) {
        repository.deleteFavorite(data)
    }
}