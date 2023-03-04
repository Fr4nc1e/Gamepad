package com.game.gamepad.di

import com.game.gamepad.feature.detail.domain.repository.DetailRepository
import com.game.gamepad.feature.detail.usecase.DetailUseCases
import com.game.gamepad.feature.detail.usecase.components.GetGameDetailUseCase
import com.game.gamepad.feature.home.domain.repository.GamesRepository
import com.game.gamepad.feature.home.usecase.GamesUseCases
import com.game.gamepad.feature.home.usecase.components.GetGamesUseCase
import com.game.gamepad.feature.search.domain.repository.SearchRepository
import com.game.gamepad.feature.search.usecase.SearchUseCases
import com.game.gamepad.feature.search.usecase.components.SearchGameUseCase
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

    @Provides
    @Singleton
    fun provideSearchUseCases(repository: SearchRepository): SearchUseCases {
        return SearchUseCases(searchGame = SearchGameUseCase(repository))
    }

    @Provides
    @Singleton
    fun provideDetailUseCases(repository: DetailRepository): DetailUseCases {
        return DetailUseCases(getGameDetail = GetGameDetailUseCase(repository))
    }
}
