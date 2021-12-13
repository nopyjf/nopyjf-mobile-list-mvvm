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

val getFavoriteListViewModel =
    module { viewModel { FavoriteListViewModel(get(), get()) } }

class FavoriteListViewModel(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    private val _state: MutableLiveData<MobileListScreenState> by lazy {
        MutableLiveData<MobileListScreenState>()
    }
    private val _filterChoice: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().also { it.postValue(-1) }
    }

    val state: LiveData<MobileListScreenState> = _state
    val filterChoice: LiveData<Int> = _filterChoice

    init {
        getFavoriteList()
    }

    fun deleteFavorite(mobileDisplay: MobileDisplay) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteUseCase(mobileDisplay.toMobile())
        }
    }

    fun getFavoriteList() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteUseCase(_filterChoice.value ?: -1)
                .collect { result ->
                    when (result) {
                        is ServiceResult.Loading -> {
                            if (_state.value?.data?.isEmpty() == true) {
                                _state.postValue(MobileListScreenState.Loading())
                            }
                        }
                        is ServiceResult.Success -> {
                            result.data
                                ?.map { it.toMobileDisplay() }
                                ?.let {
                                    _state.postValue(
                                        MobileListScreenState.MobileListSuccess(
                                            data = it
                                        )
                                    )
                                }
                        }
                        is ServiceResult.Error -> {
                            result.errorMessage?.let {
                                _state.postValue(MobileListScreenState.MobileListError(errMsg = it))
                            }
                        }
                    }
                }
        }
    }
}