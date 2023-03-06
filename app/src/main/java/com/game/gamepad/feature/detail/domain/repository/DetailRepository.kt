package com.game.gamepad.feature.detail.domain.repository

import com.game.gamepad.core.data.util.Resource
import com.game.gamepad.feature.detail.domain.models.Details
import com.game.gamepad.feature.detail.domain.models.ScreenShot
import com.game.gamepad.feature.detail.domain.models.Trailer
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getDetail(gameId: String): Flow<Resource<Details?>>
    fun getTrailer(gameId: String): Flow<Resource<List<Trailer?>?>>
    fun getScreenShots(gameId: String): Flow<Resource<List<ScreenShot?>?>>
}