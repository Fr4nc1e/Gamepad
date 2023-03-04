package com.game.gamepad.feature.home.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.gamepad.R
import com.game.gamepad.core.data.paging.PagingManager
import com.game.gamepad.core.data.util.Resource
import com.game.gamepad.core.util.CoreUiEvent
import com.game.gamepad.core.util.UiText
import com.game.gamepad.feature.home.domain.models.Game
import com.game.gamepad.feature.home.usecase.GamesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gamesUseCase: GamesUseCases
) : ViewModel() {
    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games = _games.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _eventFlow = MutableSharedFlow<CoreUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _channel = Channel<Boolean>()
    val channel = _channel.receiveAsFlow()

    private var _page = mutableStateOf(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val pagingManager = PagingManager(
        initialPage = _page.value,
        onLoadUpdated = { loadingState ->
            _isLoading.update { loadingState }
        },
        onRequest = { nextPage ->
            gamesUseCase.getGames(nextPage)
        },
        onSuccess = { items ->
            items.collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _eventFlow.emit(
                            CoreUiEvent.ShowSnackbar(
                                UiText.StringResource(R.string.fail_to_connect)
                            )
                        )
                    }
                    is Resource.Success -> {
                        result.data?.let { list ->
                            _games.update { it + list }
                            _page.value++
                            if (!_channel.isClosedForSend) {
                                _channel.send(true)
                                _channel.close()
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            pagingManager.currentPage = _page.value
            pagingManager.loadNextItems()
        }
    }
}
