package com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.ServiceResult
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.toMobileImageDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase.GetMobileImageUseCase
import com.example.nopyjf.nopyjfmobilelistmvvm.view.state.MobileDetailScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val getMobileDetailViewModel = module { viewModel { MobileDetailViewModel(get()) } }

class MobileDetailViewModel(
    private val getMobileImageUseCase: GetMobileImageUseCase
) : ViewModel() {

    private val _state: MutableState<MobileDetailScreenState> =
        mutableStateOf(MobileDetailScreenState.Loading())

    val state: State<MobileDetailScreenState> get() = _state

    fun getMobileImageList(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getMobileImageUseCase(id)
                .collect { result ->
                    when (result) {
                        is ServiceResult.Loading -> {
                            if (result.data?.isEmpty() == true) {
                                _state.value = MobileDetailScreenState.Loading()
                            }
                        }
                        is ServiceResult.Success -> {
                            result.data
                                ?.map { it.toMobileImageDisplay() }
                                ?.also {
                                    _state.value = MobileDetailScreenState.Success(data = it)
                                }
                        }
                        is ServiceResult.Error -> {
                            result.errorMessage?.let {
                                _state.value = MobileDetailScreenState.Error(errMsg = it)
                            }
                        }
                    }
                }
        }
    }
}