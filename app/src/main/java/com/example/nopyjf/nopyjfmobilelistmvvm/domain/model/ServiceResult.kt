package com.example.nopyjf.nopyjfmobilelistmvvm.domain.model

sealed class ServiceResult<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Loading<T> : ServiceResult<T>()
    class Success<T>(data: T) : ServiceResult<T>(data = data)
    class Error<T>(errorMessage: String?) : ServiceResult<T>(errorMessage = errorMessage)
}