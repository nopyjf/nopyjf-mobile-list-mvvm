package com.example.nopyjf.nopyjfmobilelistmvvm.view.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileImageDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.viewmodel.MobileDetailViewModel
import com.example.nopyjf.nopyjfmobilelistmvvm.view.DEFAULT_ID
import com.example.nopyjf.nopyjfmobilelistmvvm.view.ID_KEY
import com.example.nopyjf.nopyjfmobilelistmvvm.view.state.MobileDetailScreenState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import org.koin.androidx.compose.getViewModel

@ExperimentalPagerApi
class MobileDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent?.extras?.getInt(ID_KEY)
        setContent {
            MobileDetailScreen(id ?: DEFAULT_ID)
        }
    }
}

@Composable
@ExperimentalPagerApi
fun MobileDetailScreen(
    mobileId: Int,
    vm: MobileDetailViewModel = getViewModel()
) {
    vm.getMobileImageList(mobileId)
    val state = vm.state.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "MobileDetail")
                }
            )
        },
        content = {
            when (state) {
                is MobileDetailScreenState.Loading -> {
                    if (state.isLoading) {
                        CircularProgressIndicator()
                    }
                }
                is MobileDetailScreenState.Success -> {
                    MobileDetailViewPager(state.data)
                }
                is MobileDetailScreenState.Error -> {
                    Text(text = state.errMsg)
                }
            }
        }
    )
}

@Composable
@ExperimentalPagerApi
fun MobileDetailViewPager(images: List<MobileImageDisplay>) {
    HorizontalPager(count = images.size) { position ->
        Image(
            painter = rememberImagePainter(images[position].url),
            contentDescription = null,
            modifier = Modifier.size(96.dp),
            contentScale = ContentScale.Crop
        )
    }
}