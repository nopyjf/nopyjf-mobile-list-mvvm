package com.example.nopyjf.nopyjfmobilelistmvvm.view.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileDisplay
import com.example.nopyjf.nopyjfmobilelistmvvm.presentation.model.MobileImageDisplay
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@Composable
@ExperimentalPagerApi
fun MobileDetailViewPager(data: MobileDisplay, images: List<MobileImageDisplay>) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        HorizontalPager(
            count = images.size,
            modifier = Modifier.weight(0.35f)
        ) { position ->
            Image(
                painter = rememberImagePainter(images[position].url),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier.padding(end = 8.dp),
                fontSize = 24.sp,
                text = data.name.orEmpty()
            )
            Text(
                fontSize = 18.sp,
                text = data.brand.orEmpty()
            )
        }
        Text(
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1.0f),
            text = data.description.orEmpty()
        )
    }

}