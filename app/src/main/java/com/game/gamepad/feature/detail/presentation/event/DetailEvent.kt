package com.game.gamepad.feature.detail.presentation.event

sealed class DetailEvent {
    object PlayTrailer : DetailEvent()
    data class NavigateToWebSite(val url: String) : DetailEvent()
}
