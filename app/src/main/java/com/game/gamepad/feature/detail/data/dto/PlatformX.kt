package com.game.gamepad.feature.detail.data.dto


import com.game.gamepad.feature.detail.domain.models.Platform
import com.google.gson.annotations.SerializedName

data class PlatformX(
    @SerializedName("platform")
    val platform: PlatformXX?,
    @SerializedName("released_at")
    val releasedAt: String?,
    @SerializedName("requirements")
    val requirementsDto: RequirementsDto?
) {
    fun toPlatform() = Platform(
        name = platform?.name,
        releasedAt = releasedAt,
        requirements = requirementsDto?.toRequirements()
    )
}