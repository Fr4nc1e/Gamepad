package com.game.gamepad.feature.detail.data.dto


import com.game.gamepad.feature.detail.domain.models.Requirements
import com.google.gson.annotations.SerializedName

data class RequirementsDto(
    @SerializedName("minimum")
    val minimum: String?,
    @SerializedName("recommended")
    val recommended: String?
) {
    fun toRequirements() = Requirements(
        minimum = minimum,
        recommended = recommended
    )
}