package com.example.nopyjf.nopyjfmobilelistmvvm.view.state

import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay

sealed class MobileListScreenState(
    var data: List<MobileDisplay> = listOf(),
    var errMsg: String = ""
) {
    class Loading : MobileListScreenState()
    class MobileListSuccess(data: List<MobileDisplay>) : MobileListScreenState(data = data)
    class MobileListError(errMsg: String) : MobileListScreenState(errMsg = errMsg)

    fun isLoading(): Boolean = this is Loading
    fun isMobileListSuccess(): Boolean = this is MobileListSuccess
    fun isMobileListError(): Boolean = this is MobileListError
}

