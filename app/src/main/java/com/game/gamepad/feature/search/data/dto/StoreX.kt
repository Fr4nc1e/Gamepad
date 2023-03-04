package com.game.gamepad.feature.search.data.dto

import com.google.gson.annotations.SerializedName

data class StoreX(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?
)
