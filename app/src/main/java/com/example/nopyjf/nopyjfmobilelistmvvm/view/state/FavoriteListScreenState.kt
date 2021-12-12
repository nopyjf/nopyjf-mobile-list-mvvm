package com.example.nopyjf.nopyjfmobilelistmvvm.view.state

import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay

sealed class FavoriteListScreenState(
    var data: List<MobileDisplay> = listOf(),
    var errMsg: String = ""
) {
    class Loading : FavoriteListScreenState()
    class FavoriteListSuccess(data: List<MobileDisplay>) : FavoriteListScreenState(data = data)
    class FavoriteListError(errMsg: String) : FavoriteListScreenState(errMsg = errMsg)

    fun isLoading(): Boolean = this is Loading
    fun isMobileListSuccess(): Boolean = this is FavoriteListSuccess
    fun isMobileListError(): Boolean = this is FavoriteListError
}

