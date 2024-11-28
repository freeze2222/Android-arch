package com.compose.presentation.screens.catsScreen

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
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
    val state = viewModel.state.collectAsState()

    val pagerState = PagerState(0, pageCount = { state.value.size + 1 }) //TODO REMEMBER PAGER STATE
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
            //Text("TEST $page", color = Color.White, modifier = Modifier.fillMaxSize())
            val url = if(state.value.isEmpty()) "" else state.value[page].url
            Log.e("URL", url)
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
                    //pagerState.animateScrollToPage(
                    //    if (pagerState.currentPage == 1) pagerState.currentPage
                    //    else pagerState.currentPage)
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
                            //if (pagerState.currentPage > 5) {
                            //pagerState.animateScrollToPage(
                            //    pagerState.currentPage - 1,
                            //    animationSpec = SpringSpec(stiffness = 0.5f)
                            //)
                            //} else {
                            // pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            //}
                        }
                    }
                }) {
                Text("New image")
            }
            Spacer(modifier = Modifier.width(50.dp))
            IconButton(modifier = Modifier.height(50.dp), onClick = {
                Log.e("DEBUG1", state.value.toString())
            }) {
                Icon(Icons.Default.FavoriteBorder, "", tint = Color.White)
            }
        }
        Log.e("page", "current page: ${pagerState.currentPage}")
        Log.e("state", "current state: $state")
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    CatsScreen(rememberNavController(), contentPadding = PaddingValues())
}