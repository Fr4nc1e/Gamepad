package com.game.gamepad.feature.home.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.game.gamepad.R
import com.game.gamepad.core.util.CoreUiEvent
import com.game.gamepad.core.util.asString
import com.game.gamepad.feature.home.presentation.ui.components.GameCard
import com.game.gamepad.feature.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    onNavigate: (String) -> Unit,
    onSplashChange: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val games = viewModel.games.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val lazyListState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.channel.collect {
            if (it) onSplashChange()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is CoreUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                is CoreUiEvent.Navigate -> {
                    onNavigate(event.route)
                }
            }
        }
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(games) { game ->
            GameCard(
                game = game,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onCardClick = { onNavigate("") }
            )
        }
        if (lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            == lazyListState.layoutInfo.totalItemsCount - 1
        ) {
            item {
                ExtendedFloatingActionButton(
                    text = {
                        if (!isLoading) {
                            Text(text = stringResource(id = R.string.load_items))
                        }
                    },
                    icon = {
                        when (isLoading) {
                            true -> {
                                CircularProgressIndicator()
                            }
                            false -> {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowUp,
                                    contentDescription = stringResource(R.string.load_items)
                                )
                            }
                        }
                    },
                    onClick = { viewModel.loadNextItems() },
                    expanded = !isLoading
                )
            }
        }
    }
}
