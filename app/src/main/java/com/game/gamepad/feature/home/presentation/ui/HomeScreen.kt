package com.game.gamepad.feature.home.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.game.gamepad.core.util.CoreUiEvent
import com.game.gamepad.core.util.asString
import com.game.gamepad.feature.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    onNavigate: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val games = viewModel.games.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val endReach = viewModel.endReach.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is CoreUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                else -> {}
            }
        }
    }

    Box(modifier = modifier) {
        if (isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }

        LazyColumn(Modifier.fillMaxSize()) {
            games.let { list ->
                items(list.size) { index ->
                    val item = list[index]
                    if (index >= list.size - 1 && !endReach && !isLoading) {
                        viewModel.loadNextItems()
                    }
                    Text(text = item.name ?: "")
                }
            }
        }
    }
}
