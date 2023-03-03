package com.game.gamepad.feature.home.domain.models

data class Game(
    val backgroundImage: String?,
    val esrbRating: String?,
    val id: Int?,
    val metacritic: Int?,
    val name: String?,
    val platforms: List<String?>?,
    val rating: Float?,
    val ratingTop: Int?,
    val ratingsCount: Int?,
    val released: String?,
    val updated: String?
)
