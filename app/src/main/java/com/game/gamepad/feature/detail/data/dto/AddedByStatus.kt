package com.game.gamepad.feature.detail.data.dto


import com.google.gson.annotations.SerializedName

data class AddedByStatus(
    @SerializedName("beaten")
    val beaten: Int?,
    @SerializedName("dropped")
    val dropped: Int?,
    @SerializedName("owned")
    val owned: Int?,
    @SerializedName("toplay")
    val toplay: Int?,
    @SerializedName("yet")
    val yet: Int?
)