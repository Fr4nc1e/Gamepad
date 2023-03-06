package com.game.gamepad.feature.detail.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.gamepad.R
import com.game.gamepad.core.data.util.Resource
import com.game.gamepad.core.util.CoreUiEvent
import com.game.gamepad.core.util.UiText
import com.game.gamepad.feature.detail.domain.models.Details
import com.game.gamepad.feature.detail.domain.models.ScreenShot
import com.game.gamepad.feature.detail.domain.models.Trailer
import com.game.gamepad.feature.detail.domain.models.VideoItem
import com.game.gamepad.feature.detail.presentation.event.DetailEvent
import com.game.gamepad.feature.detail.usecase.DetailUseCases
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val player: Player,
    private val detailUseCase: DetailUseCases
) : ViewModel(){
    private val _gameDetail = MutableStateFlow(Details())
    val gameDetail = _gameDetail.asStateFlow()

    private val _gameTrailers = MutableStateFlow(emptyList<Trailer?>())

    private val _screenShots = MutableStateFlow(emptyList<ScreenShot?>())
    val screenShots = _screenShots.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isTrailerLoading = MutableStateFlow(false)
    val isTrailerLoading = _isTrailerLoading.asStateFlow()

    private val _isScreenShotsLoading = MutableStateFlow(false)
    val isScreenShotsLoading = _isScreenShotsLoading.asStateFlow()

    private val _trailerSelected = MutableStateFlow(false)
    val trailerSelected = _trailerSelected.asStateFlow()

    private val _screenShotSelected = MutableStateFlow(false)
    val screenShotSelected = _screenShotSelected.asStateFlow()

    private val _eventFlow = MutableSharedFlow<CoreUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val selectedScreenShot = MutableStateFlow("")

    val videoItems = _gameTrailers.map { trailers ->
        trailers.map { trailer ->
            trailer?.let {
                val uri = Uri.parse(it.url)
                VideoItem(
                    contentUri = uri,
                    mediaItem = MediaItem.fromUri(uri),
                    previewUrl = it.preview,
                    name = it.name
                )
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.NavigateToWebSite -> {}
            is DetailEvent.PlayTrailer -> {
                player.prepare()
                player.setMediaItem(
                    videoItems.value.find {
                        it?.contentUri == event.uri
                    }?.mediaItem ?: return
                )
                _screenShotSelected.update { false }
                _trailerSelected.update { true }
            }

            is DetailEvent.SelectScreenShot -> {
                player.pause()
                selectedScreenShot.update { event.url }
                _screenShotSelected.update { true }
                _trailerSelected.update { false }
            }
        }
    }

    init {
        savedStateHandle.get<String>("gameId")?.let {
            viewModelScope.launch {
                detailUseCase.getGameDetail(it).collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _eventFlow.emit(
                                CoreUiEvent.ShowSnackbar(
                                    UiText.StringResource(R.string.fail_to_connect)
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _isLoading.update { result.isLoading }
                        }
                        is Resource.Success -> {
                            result.data?.let { details ->
                                _gameDetail.update { details }
                            }
                        }
                    }
                }
            }
            viewModelScope.launch {
                detailUseCase.getTrailer(it).collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _eventFlow.emit(
                                CoreUiEvent.ShowSnackbar(
                                    UiText.StringResource(R.string.fail_to_connect)
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _isTrailerLoading.update { result.isLoading }
                        }
                        is Resource.Success -> {
                            result.data?.let { list ->
                                _gameTrailers.update { list }
                            }
                        }
                    }
                }
            }
            viewModelScope.launch {
                detailUseCase.getScreenShots(it).collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _eventFlow.emit(
                                CoreUiEvent.ShowSnackbar(
                                    UiText.StringResource(R.string.fail_to_connect)
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _isScreenShotsLoading.update { result.isLoading }
                        }
                        is Resource.Success -> {
                            result.data?.let { list ->
                                _screenShots.update { list }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}