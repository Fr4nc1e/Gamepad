package com.game.gamepad.feature.search.data.dto

import com.google.gson.annotations.SerializedName

data class EsrbRating(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("name_en")
    val nameEn: String?,
    @SerializedName("name_ru")
    val nameRu: String?,
    @SerializedName("slug")
    val slug: String?
)
