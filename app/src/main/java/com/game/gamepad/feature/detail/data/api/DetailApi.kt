package com.game.gamepad.feature.detail.data.api

import com.game.gamepad.feature.detail.data.dto.DetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {
    @GET("api/games/{id}")
    suspend fun getGameDetail(
        @Path("id") gameId: String
    ): DetailDto
}