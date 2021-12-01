package com.example.nopyjf.nopyjfmobilelistmvvm.view.detail

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.view.state.MobileDetailScreenState
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
@ExperimentalPagerApi
fun MobileDetailContentSection(state: MobileDetailScreenState, data: MobileDisplay?) {
    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state is MobileDetailScreenState.Success -> {
            data?.let { MobileDetailViewPager(it, state.data) }

        }
        state is MobileDetailScreenState.Error -> {
            Text(text = state.errMsg)
        }
    }
}