package com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Favorite
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.ServiceResult
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase.DeleteFavoriteUseCase
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase.GetFavoriteUseCase
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase.GetMobileUseCase
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase.InsertFavoriteUseCase
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.toMobile
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.toMobileDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.view.state.MobileListScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val getMobileListViewModel =
    module { viewModel { MobileListViewModel(get(), get(), get(), get()) } }

class MobileListViewModel(
    private val getMobileUseCase: GetMobileUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    private val _mobileList: MutableLiveData<List<MobileDisplay?>> by lazy {
        MutableLiveData<List<MobileDisplay?>>()
    }
    private val _state: MutableLiveData<MobileListScreenState> by lazy {
        MutableLiveData<MobileListScreenState>()
    }
    private val _errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val mobileList: LiveData<List<MobileDisplay?>> = _mobileList
    val state: LiveData<MobileListScreenState> = _state
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        getMobileList()
    }

    fun insertFavorite(mobileDisplay: MobileDisplay) {
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteUseCase(mobileDisplay.toMobile())
            getMobileList()
        }
    }

    fun deleteFavorite(mobileDisplay: MobileDisplay) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteUseCase(mobileDisplay.toMobile())
            getMobileList()
        }
    }

    private fun getMobileList() {
        viewModelScope.launch(Dispatchers.IO) {
            getMobileUseCase()
                .collect { result ->
                    when (result) {
                        is ServiceResult.Loading -> {
                            _state.postValue(MobileListScreenState.Loading)
                        }
                        is ServiceResult.Success -> {
                            _state.postValue(MobileListScreenState.MainListSuccess)
                            _mobileList.postValue(result.data?.map { it.toMobileDisplay() })
                            _errorMessage.postValue(null)
                            getFavoriteList()
                        }
                        is ServiceResult.Error -> {
                            _state.postValue(MobileListScreenState.MainListError)
                            _errorMessage.postValue(result.errorMessage)
                            _mobileList.postValue(null)
                        }
                    }
                }
        }
    }

    private fun getFavoriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteUseCase().collect { favorites -> updateFavorite(favorites) }
        }
    }

    private fun updateFavorite(favorites: List<Favorite>) {
        val mobiles: List<MobileDisplay?> = _mobileList.value?.toList().orEmpty()
        mobiles.onEach { mob -> mob?.favorite = isFavorite(mob?.id, favorites) }
        _mobileList.postValue(mobiles)
    }

    private fun isFavorite(mobileId: Int?, favorites: List<Favorite>): Boolean {
        return favorites.find { fav -> fav.id == mobileId }?.let { true } ?: false
    }
}