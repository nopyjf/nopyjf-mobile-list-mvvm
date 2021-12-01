package com.example.nopyjf.nopyjfmobilelistmvvm.view.state

import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileImageDisplay

sealed class MobileDetailScreenState(
    var isLoading: Boolean = true,
    var data: List<MobileImageDisplay> = listOf(),
    var errMsg: String = ""
) {
    class Loading : MobileDetailScreenState()
    class Success(data: List<MobileImageDisplay>) : MobileDetailScreenState(isLoading = false, data = data)
    class Error(errMsg: String) : MobileDetailScreenState(isLoading = false, errMsg = errMsg)
}