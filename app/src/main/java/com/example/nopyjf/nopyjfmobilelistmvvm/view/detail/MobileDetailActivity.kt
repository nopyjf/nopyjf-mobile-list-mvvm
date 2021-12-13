package com.example.nopyjf.nopyjfmobilelistmvvm.view.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.view.constant.MOBILE_DISPLAY_EXTRA
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
class MobileDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent?.extras?.getParcelable<MobileDisplay>(MOBILE_DISPLAY_EXTRA)

        setContent {
            MobileDetailScreen(
                mobileId = data?.id ?: 0,
                data = data,
                navigateBack = ::finish
            )
        }
    }
}