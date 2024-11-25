package com.compose.presentation.screens.catsScreen

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animation
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorizedAnimationSpec
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.compose.presentation.R
import com.compose.presentation.composable.EnterAnimation

@SuppressLint("ComposeMultipleContentEmitters", "ComposeContentEmitterReturningValues")
@Composable
fun CatsScreen(
    navController: NavHostController,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: CatsViewModel = hiltViewModel<CatsViewModel>(),
) {
    val state = viewModel.state.collectAsState().value
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        AnimatedVisibility(
            visibleState = MutableTransitionState(
                initialState = false
            ).apply { targetState = true },
            enter = slideInHorizontally(initialOffsetX = {state.offset}, animationSpec = tween(durationMillis = 1000)),
            exit = slideOutHorizontally(),
        ) {
            AsyncImage(
                state.url,
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
                ),
                onSuccess = {
                    viewModel.changeOffset(50)
                }
            )
        }
        Spacer(modifier = Modifier.weight(2f))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(bottom = 40.dp)
                .fillMaxWidth()
        ) {
            Icon(
                Icons.AutoMirrored.Outlined.ArrowBack,
                "",
                tint = Color.White,
                modifier = Modifier.height(50.dp)
            )
            Spacer(modifier = Modifier.width(50.dp))
            Button(modifier = Modifier
                .width(140.dp)
                .height(50.dp)
                .shadow(2.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    viewModel.getRandomCat().apply {
                        viewModel.changeOffset(0)
                    }
                }) {
                Text("New image")
            }
            Spacer(modifier = Modifier.width(50.dp))
            IconButton(modifier = Modifier.height(50.dp), onClick = {}) {
                Icon(Icons.Default.FavoriteBorder, "", tint = Color.White)
            }
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    CatsScreen(rememberNavController(), contentPadding = PaddingValues())
}