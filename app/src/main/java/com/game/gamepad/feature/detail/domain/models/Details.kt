package com.game.gamepad.feature.detail.domain.models

import com.game.gamepad.feature.detail.data.dto.EsrbRating

data class Details(
    val achievementsCount: Int? = null,
    val backgroundImage: String? = null,
    val creatorsCount: Int? = null,
    val description: String? = null,
    val developers: List<Developer>? = null,
    val esrbRating: EsrbRating? = null,
    val gameSeriesCount: Int? = null,
    val genres: List<Genre>? = null,
    val id: Int? = null,
    val metacriticUrl: String? = null,
    val moviesCount: Int? = null,
    val name: String? = null,
    val nameOriginal: String? = null,
    val platforms: List<Platform>? = null,
    val publishers: List<Publisher>? = null,
    val rating: Double? = null,
    val ratingTop: Int? = null,
    val ratings: List<Rating>? = null,
    val ratingsCount: Int? = null,
    val redditCount: Int? = null,
    val redditDescription: String? = null,
    val redditLogo: String? = null,
    val redditName: String? = null,
    val redditUrl: String? = null,
    val released: String? = null,
    val reviewsCount: Int? = null,
    val reviewsTextCount: Int? = null,
    val twitchCount: Int? = null,
    val updated: String? = null,
    val website: String? = null,
    val youtubeCount: Int? = null
)
