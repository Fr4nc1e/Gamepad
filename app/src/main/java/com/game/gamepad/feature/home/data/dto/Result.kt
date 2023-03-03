package com.game.gamepad.feature.home.data.dto

import com.game.gamepad.feature.home.domain.models.Game
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("added")
    val added: Int?,
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("metacritic")
    val metacritic: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("platforms")
    val platformDto: List<PlatformDto>?,
    @SerializedName("playtime")
    val playtime: Int?,
    @SerializedName("rating")
    val rating: Float?,
    @SerializedName("rating_top")
    val ratingTop: Int?,
    @SerializedName("ratings")
    val ratings: List<Ratings>?,
    @SerializedName("ratings_count")
    val ratingsCount: Int?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("suggestions_count")
    val suggestionsCount: Int?,
    @SerializedName("tba")
    val tba: Boolean?,
    @SerializedName("updated")
    val updated: String?
) {
    fun toGame() = Game(
        id = id,
        backgroundImage = backgroundImage,
        esrbRating = esrbRating?.name,
        metacritic = metacritic,
        name = name,
        platforms = platformDto?.map {
            it.platform?.name
        },
        rating = rating,
        ratingTop = ratingTop,
        ratingsCount = ratingsCount,
        released = released,
        updated = updated
    )
}
