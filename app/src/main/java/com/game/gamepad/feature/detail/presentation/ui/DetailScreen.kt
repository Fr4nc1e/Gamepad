package com.game.gamepad.feature.detail.presentation.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.game.gamepad.R
import com.game.gamepad.core.util.CoreUiEvent
import com.game.gamepad.core.util.asString
import com.game.gamepad.feature.detail.presentation.ui.components.BasicInfo
import com.game.gamepad.feature.detail.presentation.ui.components.DescriptionCard
import com.game.gamepad.feature.detail.presentation.ui.components.MediaContent
import com.game.gamepad.feature.detail.presentation.ui.components.RedditCard
import com.game.gamepad.feature.detail.presentation.ui.components.RequirementsCard
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
    val isExpanded = remember {
        mutableStateOf(false)
    }

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
                CircularProgressIndicator(modifier = Modifier.align(Center))
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

                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        details.apply {
                            BasicInfo(
                                name = name,
                                genres = genres,
                                released = released,
                                rating = rating
                            )

                            description?.let {
                                DescriptionCard(description = it)
                            }

                            Divider(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )

                            RedditCard(
                                redditName = redditName,
                                redditUrl = redditUrl,
                                redditCount = redditCount,
                                redditDescription = redditDescription
                            )

                            Divider(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )

                            Text(
                                text = stringResource(R.string.configuration_requirements),
                                style = MaterialTheme.typography.titleMedium
                            )

                            platforms?.apply {
                                when  {
                                    size > 1 -> {
                                        when (isExpanded.value) {
                                            true -> {
                                                forEach { platform ->
                                                    RequirementsCard(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(10.dp),
                                                        platform = platform
                                                    )
                                                }
                                            }
                                            false -> {
                                                RequirementsCard(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(10.dp),
                                                    platform = first()
                                                )
                                            }
                                        }
                                        FilledIconToggleButton(
                                            checked = isExpanded.value,
                                            onCheckedChange = {
                                                isExpanded.value = isExpanded.value.not()
                                            },
                                            modifier = Modifier
                                                .size(20.dp)
                                                .align(Alignment.End)
                                                .padding(
                                                    PaddingValues(
                                                        end = 10.dp,
                                                        bottom = 10.dp
                                                    )
                                                )
                                        ) {
                                            when (isExpanded.value) {
                                                true -> {
                                                    Icon(
                                                        imageVector = Icons.Filled.KeyboardArrowUp,
                                                        contentDescription = null
                                                    )
                                                }
                                                false -> {
                                                    Icon(
                                                        imageVector = Icons.Filled.KeyboardArrowDown,
                                                        contentDescription = null
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    else -> {
                                        RequirementsCard(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp),
                                            platform = first()
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
}
