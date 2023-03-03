package com.game.gamepad.feature.home.domain.repository

import com.game.gamepad.core.data.util.Resource
import com.game.gamepad.feature.home.domain.models.Game
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    fun getGames(page: Int, pageSize: Int): Flow<Resource<List<Game>>>
}
