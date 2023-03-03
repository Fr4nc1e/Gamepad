package com.game.gamepad.feature.home.data.api

import com.game.gamepad.feature.home.data.dto.GamesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {
    @GET("/api/games")
    suspend fun getGames(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): GamesDto
}
