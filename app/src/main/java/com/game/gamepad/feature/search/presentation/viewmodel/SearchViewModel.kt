package com.game.gamepad.feature.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.gamepad.R
import com.game.gamepad.core.data.util.Resource
import com.game.gamepad.core.presentation.ui.hub.components.navigation.destinations.Destinations
import com.game.gamepad.core.util.CoreUiEvent
import com.game.gamepad.core.util.UiText
import com.game.gamepad.feature.search.domain.models.SearchItem
import com.game.gamepad.feature.search.presentation.event.SearchEvent
import com.game.gamepad.feature.search.usecase.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : ViewModel() {
    private val _games = MutableStateFlow(emptyList<SearchItem>())
    val games = _games.asStateFlow()

    private val _gameName = MutableStateFlow("")
    val gameName = _gameName.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _eventFlow = MutableSharedFlow<CoreUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Navigate -> viewModelScope.launch {
                _eventFlow.emit(
                    CoreUiEvent.Navigate(Destinations.Detail.route + "/${event.route}")
                )
            }
            is SearchEvent.SearchGame -> {
                searchGame()
            }
            is SearchEvent.InputName -> {
                _gameName.update { event.gameName }
            }
        }
    }

    private fun searchGame() {
        viewModelScope.launch {
            searchUseCases.searchGame(_gameName.value).collect { result ->
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
                        result.data?.let { list ->
                            _games.update { list }
                        }
                    }
                }
            }
        }
    }
}
