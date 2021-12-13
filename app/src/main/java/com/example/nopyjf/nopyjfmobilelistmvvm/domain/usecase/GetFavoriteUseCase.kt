package com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase

import com.example.nopyjf.nopyjfmobilelistmvvm.data.repository.MobileRepository
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Favorite
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Mobile
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.ServiceResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.dsl.module
import java.lang.Exception

val getFavoriteUseCaseModule = module { single { GetFavoriteUseCase(get()) } }

class GetFavoriteUseCase(
    private val repository: MobileRepository
) {
    operator fun invoke(filterChoice: Int): Flow<ServiceResult<List<Mobile>>> = flow {
        try {
            emit(ServiceResult.Loading<List<Mobile>>())

            val mobiles = repository.getMobileList()
            val favorites = repository.getFavorites()

            addFavorite(mobiles = mobiles, favorites = favorites)
            val filteredMobiles = filterUnFavorite(mobiles = mobiles)
            val orderedMobiles = orderFavorite(
                filterChoice = filterChoice,
                mobiles = filteredMobiles
            )

            emit(ServiceResult.Success<List<Mobile>>(data = orderedMobiles))
        } catch (e: Exception) {
            emit(ServiceResult.Error<List<Mobile>>(errorMessage = e.localizedMessage))
        }
    }

    private fun addFavorite(mobiles: List<Mobile>, favorites: List<Favorite>) {
        mobiles.forEach { mob -> mob.favorite = favorites.find { it.id == mob.id } }
    }

    private fun filterUnFavorite(mobiles: List<Mobile>): List<Mobile> {
        return mobiles.filter { mob -> mob.favorite != null }
    }

    private fun orderFavorite(filterChoice: Int, mobiles: List<Mobile>): List<Mobile> {
        return when (filterChoice) {
            0 -> mobiles.sortedBy { it.price }
            1 -> mobiles.sortedByDescending { it.price }
            2 -> mobiles.sortedByDescending { it.rating }
            else -> mobiles
        }
    }
}