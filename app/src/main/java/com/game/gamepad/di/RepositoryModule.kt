package com.game.gamepad.di

import com.game.gamepad.feature.home.data.api.GamesApi
import com.game.gamepad.feature.home.data.repository.GamesRepositoryImpl
import com.game.gamepad.feature.home.domain.repository.GamesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideGamesRepository(api: GamesApi): GamesRepository {
        return GamesRepositoryImpl(api)
    }
}
