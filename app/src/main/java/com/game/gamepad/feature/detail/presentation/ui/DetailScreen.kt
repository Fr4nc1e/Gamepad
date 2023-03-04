package com.game.gamepad.feature.detail.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.game.gamepad.core.util.CoreUiEvent
import com.game.gamepad.core.util.asString
import com.game.gamepad.feature.detail.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val details = viewModel.gameDetail.collectAsState().value

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

    Column(modifier) {
        details.name?.let {
            Text(text = it)
        }
    }
}
