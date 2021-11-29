package com.example.nopyjf.nopyjfmobilelistmvvm.di

import com.example.nopyjf.nopyjfmobilelistmvvm.data.api.getMobileApiModule
import com.example.nopyjf.nopyjfmobilelistmvvm.data.repository.getMobileRepositoryModule
import com.example.nopyjf.nopyjfmobilelistmvvm.data.service.getMobileServiceModule
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase.getMobileUseCaseModule
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.getMobileListViewModel

val getMobileModule = arrayOf(
    getMobileListViewModel,
    getMobileUseCaseModule,
    getMobileRepositoryModule,
    getMobileApiModule,
    getMobileServiceModule
)

val moduleList = listOf(
    *getMobileModule
)