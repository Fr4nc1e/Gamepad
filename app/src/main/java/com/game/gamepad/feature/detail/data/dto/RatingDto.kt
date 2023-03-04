package com.game.gamepad.feature.detail.data.dto


import com.game.gamepad.feature.detail.domain.models.Rating
import com.google.gson.annotations.SerializedName

data class RatingDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("percent")
    val percent: Double?,
    @SerializedName("title")
    val title: String?
) {
    fun toRating() = Rating(
        count = count,
        id = id,
        percent = percent,
        title = title
    )
}