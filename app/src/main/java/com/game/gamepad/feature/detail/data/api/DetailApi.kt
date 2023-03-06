package com.game.gamepad.feature.detail.data.api

import com.game.gamepad.feature.detail.data.dto.DetailDto
import com.game.gamepad.feature.detail.data.dto.ScreenShotsDto
import com.game.gamepad.feature.detail.data.dto.TrailerDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {
    @GET("api/games/{id}")
    suspend fun getGameDetail(
        @Path("id") gameId: String
    ): DetailDto
    @GET("api/games/{id}/movies")
    suspend fun getTrailer(
        @Path("id") gameId: String
    ): TrailerDto

    @GET("api/games/{game_pk}/screenshots")
    suspend fun getScreenShots(
        @Path("game_pk") gameId: String
    ): ScreenShotsDto
}