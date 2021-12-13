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
    operator fun invoke(filterChoice: Int): Flow<ServiceResult<List<Mobile>>> = flow {
        try {
            emit(ServiceResult.Loading<List<Mobile>>())
            val mobiles = repository.getMobileList()
            val favorites = repository.getFavorites()

            addFavorite(mobiles = mobiles, favorites = favorites)
            val orderedMobiles = orderedMobiles(
                filterChoice = filterChoice,
                mobiles = mobiles
            )

            emit(ServiceResult.Success<List<Mobile>>(data = orderedMobiles))
        } catch (e: Exception) {
            emit(ServiceResult.Error<List<Mobile>>(errorMessage = e.localizedMessage))
        }
    }

    private fun addFavorite(mobiles: List<Mobile>, favorites: List<Favorite>) {
        mobiles.forEach { mob -> mob.favorite = favorites.find { it.id == mob.id } }
    }

    private fun orderedMobiles(filterChoice: Int, mobiles: List<Mobile>): List<Mobile> {
        return when (filterChoice) {
            0 -> mobiles.sortedBy { it.price }
            1 -> mobiles.sortedByDescending { it.price }
            2 -> mobiles.sortedByDescending { it.rating }
            else -> mobiles
        }
    }
}