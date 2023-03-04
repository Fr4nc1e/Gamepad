package com.game.gamepad.feature.detail.data.dto


import com.game.gamepad.feature.detail.domain.models.Publisher
import com.google.gson.annotations.SerializedName

data class PublisherDto(
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
    fun toPublisher() = Publisher(
        gamesCount = gamesCount,
        id = id,
        imageBackground = imageBackground,
        name = name
    )
}