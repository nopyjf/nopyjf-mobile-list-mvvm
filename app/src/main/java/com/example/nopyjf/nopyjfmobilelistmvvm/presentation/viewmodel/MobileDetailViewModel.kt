package com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.usecase.GetMobileImageUseCase
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileImageDisplay

class MobileDetailViewModel(
    private val getMobileImageUseCase: GetMobileImageUseCase
) : ViewModel() {
    private val _mobileImageList: MutableLiveData<List<MobileImageDisplay>> by lazy {

    }

    val mobileImageList: LiveData<List<MobileImageDisplay>> = _mobileImageList

    private fun getMobileImageList() {

    }
}