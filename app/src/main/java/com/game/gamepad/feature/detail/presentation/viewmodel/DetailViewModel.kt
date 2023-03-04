package com.game.gamepad.feature.detail.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.gamepad.R
import com.game.gamepad.core.data.util.Resource
import com.game.gamepad.core.util.CoreUiEvent
import com.game.gamepad.core.util.UiText
import com.game.gamepad.feature.detail.domain.models.Details
import com.game.gamepad.feature.detail.presentation.event.DetailEvent
import com.game.gamepad.feature.detail.usecase.DetailUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val detailUseCase: DetailUseCases
) : ViewModel(){
    private val _gameDetail = MutableStateFlow(Details())
    val gameDetail = _gameDetail.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _eventFlow = MutableSharedFlow<CoreUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.NavigateToWebSite -> {}
            DetailEvent.PlayTrailer -> {}
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
        }

    }
}