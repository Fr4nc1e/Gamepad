package com.game.gamepad.feature.detail.presentation.event

import android.net.Uri

sealed class DetailEvent {
    data class PlayTrailer(val uri: Uri) : DetailEvent()
    data class NavigateToWebSite(val url: String) : DetailEvent()
    data class SelectScreenShot(val url: String) : DetailEvent()
}
