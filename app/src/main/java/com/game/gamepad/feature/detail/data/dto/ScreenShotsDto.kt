package com.game.gamepad.feature.detail.data.dto


import com.game.gamepad.feature.detail.domain.models.ScreenShot
import com.google.gson.annotations.SerializedName

data class ScreenShotsDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: Any?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val results: List<ResultX>?
) {
    fun toScreenShotsList(): List<ScreenShot?>? {
        return results?.map {
            it.toScreenShot()
        }
    }
}