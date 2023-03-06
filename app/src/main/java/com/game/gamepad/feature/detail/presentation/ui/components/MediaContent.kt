package com.game.gamepad.feature.detail.presentation.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import coil.compose.AsyncImage
import com.game.gamepad.core.util.ImageLoader
import com.game.gamepad.feature.detail.presentation.event.DetailEvent
import com.game.gamepad.feature.detail.presentation.viewmodel.DetailViewModel
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun MediaContent(
    modifier: Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val trailerSelected = viewModel.trailerSelected.collectAsState().value
    val screenShotSelected = viewModel.screenShotSelected.collectAsState().value
    val details = viewModel.gameDetail.collectAsState().value
    val gameTrailers = viewModel.videoItems.collectAsState().value
    val screenShots = viewModel.screenShots.collectAsState().value
    val selectedScreenShot = viewModel.selectedScreenShot.collectAsState().value
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(modifier = modifier) {
        Card {
            when {
                trailerSelected -> {
                    AndroidView(
                        factory = { context ->
                            StyledPlayerView(context).also {
                                it.player = viewModel.player
                            }
                        },
                        update = {
                            when (lifecycle) {
                                Lifecycle.Event.ON_PAUSE -> {
                                    it.onPause()
                                    it.player?.pause()
                                }
                                Lifecycle.Event.ON_RESUME -> {
                                    it.onResume()
                                }
                                else -> Unit
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1920f / 1080f)
                            .animateContentSize()
                    )
                }
                screenShotSelected -> {
                    ImageLoader(
                        modifier = Modifier
                            .aspectRatio(1920f / 1080f)
                            .fillMaxSize()
                            .animateContentSize(),
                        url = selectedScreenShot
                    )
                }
                else -> {
                    ImageLoader(
                        modifier = Modifier
                            .aspectRatio(1920f / 1080f)
                            .fillMaxSize()
                            .animateContentSize(),
                        url = details.backgroundImage
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            LazyRow {
                gameTrailers.forEach {
                    item {
                        Card(modifier = Modifier.padding(10.dp)) {
                            AsyncImage(
                                model = it?.previewUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .aspectRatio(1920f / 1080f)
                                    .animateContentSize()
                                    .clickable {
                                        it?.contentUri?.let { uri ->
                                            viewModel.onEvent(DetailEvent.PlayTrailer(uri))
                                        }
                                    }
                            )
                        }
                    }
                }

                screenShots.forEach { screenShot ->
                    screenShot?.image.let { url ->
                        url?.let {
                            item {
                                Card(modifier = Modifier.padding(10.dp)) {
                                    AsyncImage(
                                        model = it,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .aspectRatio(1920f / 1080f)
                                            .animateContentSize()
                                            .clickable {
                                                viewModel.onEvent(DetailEvent.SelectScreenShot(it))
                                            }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}