package com.game.gamepad.feature.detail.usecase.components

import com.game.gamepad.feature.detail.domain.repository.DetailRepository

class GetScreenShotsUseCase(
    private val repository: DetailRepository
) {
    operator fun invoke(gameId: String) = repository.getScreenShots(gameId)
}