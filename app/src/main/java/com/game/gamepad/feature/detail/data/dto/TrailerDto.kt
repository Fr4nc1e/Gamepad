package com.game.gamepad.feature.detail.data.dto


import com.google.gson.annotations.SerializedName

data class TrailerDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: Any?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val results: List<Result?>?
) {
    fun toTrailers() = results?.map { it?.toTrailer() }
}