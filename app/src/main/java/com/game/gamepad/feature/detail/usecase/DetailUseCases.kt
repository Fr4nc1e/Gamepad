package com.game.gamepad.feature.detail.usecase

import com.game.gamepad.feature.detail.usecase.components.GetGameDetailUseCase
import com.game.gamepad.feature.detail.usecase.components.GetScreenShotsUseCase
import com.game.gamepad.feature.detail.usecase.components.GetTrailerUseCase

data class DetailUseCases(
    val getGameDetail: GetGameDetailUseCase,
    val getTrailer: GetTrailerUseCase,
    val getScreenShots: GetScreenShotsUseCase
)
