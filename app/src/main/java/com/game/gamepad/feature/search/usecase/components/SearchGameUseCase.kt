package com.game.gamepad.feature.search.usecase.components

import com.game.gamepad.feature.search.domain.repository.SearchRepository

class SearchGameUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(gameName: String) = repository.searchGame(gameName)
}