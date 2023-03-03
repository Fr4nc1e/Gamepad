package com.game.gamepad.feature.home.data.dto

import com.google.gson.annotations.SerializedName

data class PlatformDto(
    @SerializedName("platform")
    val platform: PlatformX?,
    @SerializedName("released_at")
    val releasedAt: String?,
    @SerializedName("requirements")
    val requirementsDto: RequirementsDto?
)
