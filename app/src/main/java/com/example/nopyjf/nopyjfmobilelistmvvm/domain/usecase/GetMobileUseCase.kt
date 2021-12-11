package com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase

import com.example.nopyjf.nopyjfmobilelistmvvm.data.repository.MobileRepository
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Favorite
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Mobile
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.ServiceResult
import com.example.nopyjf.nopyjfmobilelistmvvm.view.state.MobileListScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.dsl.module
import java.lang.Exception

val getMobileUseCaseModule = module { single { GetMobileUseCase(get()) } }

class GetMobileUseCase(
    private val repository: MobileRepository
) {
    operator fun invoke(): Flow<ServiceResult<List<Mobile>>> = flow {
        try {
            emit(ServiceResult.Loading<List<Mobile>>())
            val mobiles = repository.getMobileList()
            val favorites = repository.getFavorites()
            addFavorite(mobiles = mobiles, favorites = favorites)
            emit(ServiceResult.Success<List<Mobile>>(data = mobiles))
        } catch (e: Exception) {
            emit(ServiceResult.Error<List<Mobile>>(errorMessage = e.localizedMessage))
        }
    }

    private fun addFavorite(mobiles: List<Mobile>, favorites: List<Favorite>) {
        mobiles.forEach { mob -> mob.favorite = favorites.find { it.id == mob.id } }
    }
}