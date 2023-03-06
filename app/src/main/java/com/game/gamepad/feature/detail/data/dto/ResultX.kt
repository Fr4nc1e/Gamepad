package com.game.gamepad.feature.detail.data.dto


import com.game.gamepad.feature.detail.domain.models.ScreenShot
import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("is_deleted")
    val isDeleted: Boolean?,
    @SerializedName("width")
    val width: Int?
) {
    fun toScreenShot() = ScreenShot(image)
}