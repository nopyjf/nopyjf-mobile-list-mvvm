package com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.ServiceResult
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.toMobileDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase.GetMobileUseCase
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.view.state.MobileListScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val getMobileListViewModel = module { viewModel { MobileListViewModel(get()) } }

class MobileListViewModel(
    private val getMobileUseCase: GetMobileUseCase
) : ViewModel() {

    private val _mobileList: MutableLiveData<List<MobileDisplay?>> by lazy {
        MutableLiveData<List<MobileDisplay?>>().also { getMobileList() }
    }
    private val _state: MutableLiveData<MobileListScreenState> by lazy {
        MutableLiveData<MobileListScreenState>()
    }
    private val _errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val mobileList: LiveData<List<MobileDisplay?>> = _mobileList
    val state: LiveData<MobileListScreenState> = _state
    val errorMessage: LiveData<String?> = _errorMessage

    private fun getMobileList() {
        viewModelScope.launch {
            getMobileUseCase()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    when (result) {
                        is ServiceResult.Loading -> {
                            _state.postValue(MobileListScreenState.Loading)
                        }
                        is ServiceResult.Success -> {
                            _state.postValue(MobileListScreenState.MainListSuccess)
                            _mobileList.postValue(result.data?.map { it.toMobileDisplay() })
                            _errorMessage.postValue(null)
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
}