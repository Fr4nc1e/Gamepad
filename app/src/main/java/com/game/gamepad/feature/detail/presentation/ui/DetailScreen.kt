package com.game.gamepad.feature.detail.presentation.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.game.gamepad.core.util.CoreUiEvent
import com.game.gamepad.core.util.asString
import com.game.gamepad.feature.detail.presentation.ui.components.MediaContent
import com.game.gamepad.feature.detail.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val details = viewModel.gameDetail.collectAsState().value
    val trailerLoading = viewModel.isTrailerLoading.collectAsState().value
    val detailLoading = viewModel.isLoading.collectAsState().value
    val screenShotsLoading = viewModel.isScreenShotsLoading.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is CoreUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        when (trailerLoading || detailLoading || screenShotsLoading) {
            true -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            false -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .animateContentSize()
                        .verticalScroll(scrollState)
                ) {
                    MediaContent(modifier = Modifier.fillMaxWidth())

                    Column(modifier = Modifier.padding(10.dp)) {
                        details.apply {
                            name?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
