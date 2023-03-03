package com.game.gamepad.feature.home.data.dto

import com.google.gson.annotations.SerializedName

data class Ratings(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val exceptional: String?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("percent")
    val percent: Float?
)
