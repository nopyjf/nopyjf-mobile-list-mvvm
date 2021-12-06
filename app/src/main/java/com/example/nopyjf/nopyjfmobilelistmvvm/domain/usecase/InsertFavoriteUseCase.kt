package com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase

import com.example.nopyjf.nopyjfmobilelistmvvm.data.repository.MobileRepository
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Mobile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.dsl.module

val getInsertFavoriteUseCaseModule = module { single { InsertFavoriteUseCase(get()) } }

class InsertFavoriteUseCase(
    private val repository: MobileRepository
) {
    suspend operator fun invoke(data: Mobile) {
        repository.insertFavorite(data)
    }
}