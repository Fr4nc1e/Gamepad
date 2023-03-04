package com.game.gamepad.feature.home.presentation.event

sealed class HomeEvent {
    object LoadItems : HomeEvent()
    data class Navigate(val route: String) : HomeEvent()
}
