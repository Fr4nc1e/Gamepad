package com.game.gamepad.feature.search.presentation.event

sealed class SearchEvent {
    object SearchGame : SearchEvent()
    data class InputName(val gameName: String) : SearchEvent()
    data class Navigate(val route: String) : SearchEvent()
}
