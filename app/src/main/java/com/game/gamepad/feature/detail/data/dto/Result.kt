package com.game.gamepad.feature.detail.data.dto


import com.game.gamepad.feature.detail.domain.models.Trailer
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("preview")
    val preview: String?
) {
    fun toTrailer() = Trailer(
        id = id,
        name = name,
        preview = preview,
        url = `data`?.max
    )
}