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
import kotlinx.coroutines.flow.collect
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

    val mobileList: LiveData<List<MobileDisplay?>> = _mobileList

    var errorMessage: String? = null
    lateinit var state: MobileListScreenState

    private fun getMobileList() {
        viewModelScope.launch {
            getMobileUseCase().collect { result ->
                when (result) {
                    is ServiceResult.Loading -> {
                        state = MobileListScreenState.Loading
                    }
                    is ServiceResult.Success -> {
                        state = MobileListScreenState.MainListSuccess
                        _mobileList.postValue(result.data?.map { it.toMobileDisplay() })
                        errorMessage = null
                    }
                    is ServiceResult.Error -> {
                        state = MobileListScreenState.MainListError
                        errorMessage = result.errorMessage
                        _mobileList.postValue(null)
                    }
                }
            }
        }
    }
}