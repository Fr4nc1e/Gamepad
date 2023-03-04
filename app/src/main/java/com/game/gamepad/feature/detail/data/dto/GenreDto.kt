package com.game.gamepad.feature.detail.data.dto


import com.game.gamepad.feature.detail.domain.models.Genre
import com.google.gson.annotations.SerializedName

data class GenreDto(
    @SerializedName("games_count")
    val gamesCount: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_background")
    val imageBackground: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?
) {
    fun toGenre() = Genre(
        id = id,
        name = name
    )
}