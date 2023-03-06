package com.game.gamepad.feature.detail.domain.models

import android.net.Uri
import com.google.android.exoplayer2.MediaItem

data class VideoItem(
    val contentUri: Uri,
    val previewUrl: String?,
    val mediaItem: MediaItem,
    val name: String?
)
