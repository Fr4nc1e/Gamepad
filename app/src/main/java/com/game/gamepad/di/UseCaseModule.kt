package com.game.gamepad.di

import com.game.gamepad.feature.home.domain.repository.GamesRepository
import com.game.gamepad.feature.home.usecase.GamesUseCases
import com.game.gamepad.feature.home.usecase.components.GetGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGamesUseCases(repository: GamesRepository): GamesUseCases {
        return GamesUseCases(getGames = GetGamesUseCase(repository))
    }
}
