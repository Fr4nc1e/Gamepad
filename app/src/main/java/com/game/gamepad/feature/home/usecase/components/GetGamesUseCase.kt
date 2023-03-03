package com.game.gamepad.feature.home.usecase.components

import com.game.gamepad.core.data.util.ApiConstants
import com.game.gamepad.feature.home.domain.repository.GamesRepository

class GetGamesUseCase(
    private val repository: GamesRepository
) {
    operator fun invoke(page: Int) = repository.getGames(page, ApiConstants.PAGE_SIZE)
}
