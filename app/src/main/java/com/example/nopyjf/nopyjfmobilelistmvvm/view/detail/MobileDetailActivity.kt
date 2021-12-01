package com.example.nopyjf.nopyjfmobilelistmvvm.view.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
class MobileDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent?.extras?.getInt(ID_EXTRA)
        val data = intent?.extras?.getParcelable<MobileDisplay>(DATA_EXTRA)

        setContent {
            MobileDetailScreen(
                mobileId = id ?: DEFAULT_ID,
                data = data,
                navigateBack = ::finish)
        }
    }

    companion object {
        private const val DEFAULT_ID = 0
        private const val ID_EXTRA = "ID_EXTRA"
        private const val DATA_EXTRA = "DATA_EXTRA"
    }
}