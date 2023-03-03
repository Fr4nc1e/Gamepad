package com.game.gamepad.core.util

sealed class CoreUiEvent {
    data class ShowSnackbar(val uiText: UiText) : CoreUiEvent()
    data class Navigate(val route: String) : CoreUiEvent()
}
