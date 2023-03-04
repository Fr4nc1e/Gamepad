package com.game.gamepad.feature.search.data.dto

import com.game.gamepad.feature.search.domain.models.SearchResults
import com.google.gson.annotations.SerializedName

data class SearchDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("user_platforms")
    val userPlatforms: Boolean?
) {
    fun toSearchResults() = SearchResults(
        searchItems = results?.map { it.toSearchItem() }
    )
}
