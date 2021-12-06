package com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase

import com.example.nopyjf.nopyjfmobilelistmvvm.data.repository.MobileRepository
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Favorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.dsl.module

val getFavoriteUseCaseModule = module { single { GetFavoriteUseCase(get()) } }

class GetFavoriteUseCase(
    private val repository: MobileRepository
) {
    operator fun invoke(): Flow<List<Favorite>> = flow { emit(repository.getFavorites()) }
}