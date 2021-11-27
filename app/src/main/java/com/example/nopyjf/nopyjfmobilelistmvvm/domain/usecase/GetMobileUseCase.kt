package com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase

import com.example.nopyjf.nopyjfmobilelistmvvm.data.repository.MobileRepository
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Mobile
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.ServiceResult
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
            val data = repository.getMobileList()
            emit(ServiceResult.Success<List<Mobile>>(data = data))
        } catch (e: Exception) {
            emit(ServiceResult.Error<List<Mobile>>(errorMessage = e.localizedMessage))
        }
    }
}