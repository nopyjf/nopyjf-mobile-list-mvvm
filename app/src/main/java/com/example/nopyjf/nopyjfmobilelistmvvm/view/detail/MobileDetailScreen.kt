package com.example.nopyjf.nopyjfmobilelistmvvm.view.detail

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.MobileDetailViewModel
import com.example.nopyjf.nopyjfmobilelistmvvm.view.state.MobileDetailScreenState
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.androidx.compose.getViewModel

@Composable
@ExperimentalPagerApi
fun MobileDetailScreen(
    mobileId: Int,
    data: MobileDisplay?,
    navigateBack: () -> Unit,
    vm: MobileDetailViewModel = getViewModel()
) {
    vm.getMobileImageList(mobileId)
    val state = vm.state.value

    Scaffold(
        topBar = { MobileDetailTopAppBar(navigateBack) },
        content = { MobileDetailContentSection(state, data) }
    )
}