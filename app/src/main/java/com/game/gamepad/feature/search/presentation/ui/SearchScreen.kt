package com.game.gamepad.feature.search.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.game.gamepad.R
import com.game.gamepad.core.util.CoreUiEvent
import com.game.gamepad.core.util.asString
import com.game.gamepad.feature.search.presentation.event.SearchEvent
import com.game.gamepad.feature.search.presentation.ui.component.SearchItemCard
import com.game.gamepad.feature.search.presentation.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    onNavigate: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val gameName = viewModel.gameName.collectAsState().value
    val games = viewModel.games.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val lazyListState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 0

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

    Box(modifier = modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }

        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            state = lazyListState
        ) {
            item {
                OutlinedTextField(
                    value = gameName,
                    onValueChange = {
                        viewModel.onEvent(SearchEvent.InputName(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .graphicsLayer {
                            scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                            translationY = scrolledY * 0.5f
                            previousOffset = lazyListState.firstVisibleItemScrollOffset
                        },
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.onEvent(SearchEvent.SearchGame)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = stringResource(id = R.string.search)
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        viewModel.onEvent(SearchEvent.SearchGame)
                    }),
                    shape = RoundedCornerShape(16.dp)
                )
            }
            items(games) {
                SearchItemCard(modifier = Modifier.padding(10.dp), item = it) {
                    it.id?.let { id ->
                        viewModel.onEvent(SearchEvent.Navigate(id.toString()))
                    }
                }
            }
        }
    }
}
