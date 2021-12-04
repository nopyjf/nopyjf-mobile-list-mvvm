package com.example.nopyjf.nopyjfmobilelistmvvm.view.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        Box(
            modifier = Modifier.weight(0.35f)
        ) {
            HorizontalPager(
                modifier = Modifier.align(Alignment.Center),
                count = images.size,
            ) { position ->
                Image(
                    painter = rememberImagePainter(images[position].url),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = "Rating: ${data.rating}",
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.BottomStart)
            )

            Text(
                text = "Price: $${data.price}",
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.BottomEnd)
            )
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
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