package com.compose.presentation.screens.catsScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.compose.presentation.R
import kotlinx.coroutines.launch

@SuppressLint("ComposeMultipleContentEmitters", "ComposeContentEmitterReturningValues")
@Composable
fun CatsScreen(
    navController: NavHostController,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: CatsViewModel = hiltViewModel<CatsViewModel>(),
) {
    val state = viewModel.state.collectAsState().value
    val pagerState = rememberPagerState { state.data.size }
    var isFavourited by remember { mutableStateOf(state.data[pagerState.currentPage].isFavourited) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) { page ->
            val url = state.data[page].url
            AsyncImage(
                url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    ),
                contentScale = ContentScale.Fit,
                placeholder = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current).data(
                        R.drawable.cat
                    ).decoderFactory(ImageDecoderDecoder.Factory()).build()
                )
            )

        }
        Spacer(modifier = Modifier.weight(2f))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(bottom = 40.dp)
                .fillMaxWidth()
        ) {
            IconButton(modifier = Modifier.height(50.dp), onClick = {
                coroutineScope.launch {
                    isFavourited =
                        state.data.getOrElse(pagerState.currentPage - 1) { ImageData() }.isFavourited
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)

                }
            }) {
                Icon(
                    Icons.AutoMirrored.Outlined.ArrowBack,
                    "",
                    tint = Color.White,
                )
            }

            Spacer(modifier = Modifier.width(50.dp))
            Button(modifier = Modifier
                .width(140.dp)
                .height(50.dp)
                .shadow(2.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    viewModel.updateList().apply {
                        coroutineScope.launch {
                            isFavourited =
                                state.data.getOrElse(pagerState.currentPage + 1) { ImageData() }.isFavourited
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }) {
                Text("New image")
            }
            Spacer(modifier = Modifier.width(50.dp))
            IconButton(modifier = Modifier.height(50.dp), onClick = {
                viewModel.addFavourite(pagerState.currentPage)
                isFavourited = !isFavourited
            }) {
                Icon(
                    if (isFavourited) Icons.Default.Favorite
                    else Icons.Default.FavoriteBorder, "", tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    CatsScreen(rememberNavController(), contentPadding = PaddingValues())
}