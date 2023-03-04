package com.game.gamepad.feature.detail.domain.repository

import com.game.gamepad.core.data.util.Resource
import com.game.gamepad.feature.detail.domain.models.Details
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getDetail(gameId: String): Flow<Resource<Details?>>
}