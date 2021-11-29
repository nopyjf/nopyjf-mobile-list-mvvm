package com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase

import com.example.nopyjf.nopyjfmobilelistmvvm.data.repository.MobileRepository
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.MobileImage
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.ServiceResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.dsl.module
import java.lang.Exception

val getMobileImageUseCaseModule = module { single { GetMobileImageUseCase(get()) } }

class GetMobileImageUseCase(
    private val repository: MobileRepository
) {
    operator fun invoke(): Flow<ServiceResult<List<MobileImage>>> = flow {
        try {
            emit(ServiceResult.Loading<List<MobileImage>>())
            val data = repository.getMobileImageList()
            emit(ServiceResult.Success<List<MobileImage>>(data = data))
        } catch (e: Exception) {
            emit(ServiceResult.Error<List<MobileImage>>(errorMessage = e.localizedMessage))
        }
    }
}