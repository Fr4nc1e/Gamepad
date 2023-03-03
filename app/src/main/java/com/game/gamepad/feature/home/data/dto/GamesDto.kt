package com.game.gamepad.feature.home.data.dto

import com.game.gamepad.feature.home.domain.models.GamesList
import com.google.gson.annotations.SerializedName

data class GamesDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<Result>?
) {
    fun toGamesList() = GamesList(
        games = results?.map { it.toGame() }
    )
}
