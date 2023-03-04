package com.game.gamepad.feature.detail.usecase.components

import com.game.gamepad.feature.detail.domain.repository.DetailRepository

class GetGameDetailUseCase(
    private val repository: DetailRepository
) {
    operator fun invoke(gameId: String) = repository.getDetail(gameId)
}