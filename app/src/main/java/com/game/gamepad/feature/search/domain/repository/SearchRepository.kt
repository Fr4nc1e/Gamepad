package com.game.gamepad.feature.search.domain.repository

import com.game.gamepad.core.data.util.Resource
import com.game.gamepad.feature.search.domain.models.SearchItem
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchGame(gameName: String): Flow<Resource<List<SearchItem>>>
}