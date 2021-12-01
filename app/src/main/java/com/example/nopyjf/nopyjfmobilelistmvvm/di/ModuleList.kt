package com.example.nopyjf.nopyjfmobilelistmvvm.di

import com.example.nopyjf.nopyjfmobilelistmvvm.data.api.getMobileApiModule
import com.example.nopyjf.nopyjfmobilelistmvvm.data.repository.getMobileRepositoryModule
import com.example.nopyjf.nopyjfmobilelistmvvm.data.service.getMobileServiceModule
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase.getMobileImageUseCaseModule
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase.getMobileUseCaseModule
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.getMobileDetailViewModel
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.getMobileListViewModel

val getMobileModule = arrayOf(
    getMobileListViewModel,
    getMobileDetailViewModel,
    getMobileUseCaseModule,
    getMobileImageUseCaseModule,
    getMobileRepositoryModule,
    getMobileApiModule,
    getMobileServiceModule
)

val moduleList = listOf(
    *getMobileModule
)