package com.example.nopyjf.nopyjfmobilelistmvvm.di

import com.example.nopyjf.nopyjfmobilelistmvvm.data.api.getMobileApiModule
import com.example.nopyjf.nopyjfmobilelistmvvm.data.database.getMobileDatabaseModule
import com.example.nopyjf.nopyjfmobilelistmvvm.data.repository.getMobileRepositoryModule
import com.example.nopyjf.nopyjfmobilelistmvvm.data.service.getMobileServiceModule
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase.*
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.getFavoriteListViewModel
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.getMobileDetailViewModel
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.getMobileListViewModel

val getViewModelModule = arrayOf(
    getMobileListViewModel,
    getMobileDetailViewModel,
    getFavoriteListViewModel
)

val getUseCaseModule = arrayOf(
    getMobileUseCaseModule,
    getMobileImageUseCaseModule,
    getFavoriteUseCaseModule,
    getInsertFavoriteUseCaseModule,
    getDeleteFavoriteUseCase
)

val getRepositoryModule = arrayOf(
    getMobileRepositoryModule
)

val getApiModule = arrayOf(
    getMobileApiModule
)

val getServiceModule = arrayOf(
    getMobileServiceModule
)

val getDatabaseModule = arrayOf(
    getMobileDatabaseModule
)

val moduleList = listOf(
    *getViewModelModule,
    *getUseCaseModule,
    *getRepositoryModule,
    *getApiModule,
    *getServiceModule,
    *getDatabaseModule
)