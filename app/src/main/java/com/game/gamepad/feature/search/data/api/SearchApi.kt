package com.game.gamepad.feature.search.data.api

import com.game.gamepad.feature.search.data.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("api/games?parent_platforms=1,2,3&search_precise=false&search_exact=false")
    suspend fun searchGames(
        @Query("search") gameName: String
    ): SearchDto
}