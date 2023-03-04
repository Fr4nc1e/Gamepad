package com.game.gamepad.di

import com.game.gamepad.feature.detail.data.api.DetailApi
import com.game.gamepad.feature.detail.data.repository.DetailRepositoryImpl
import com.game.gamepad.feature.detail.domain.repository.DetailRepository
import com.game.gamepad.feature.home.data.api.GamesApi
import com.game.gamepad.feature.home.data.repository.GamesRepositoryImpl
import com.game.gamepad.feature.home.domain.repository.GamesRepository
import com.game.gamepad.feature.search.data.api.SearchApi
import com.game.gamepad.feature.search.data.repository.SearchRepositoryImpl
import com.game.gamepad.feature.search.domain.repository.SearchRepository
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

    @Provides
    @Singleton
    fun provideSearchRepository(api: SearchApi): SearchRepository {
        return SearchRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(api: DetailApi): DetailRepository {
        return DetailRepositoryImpl(api)
    }
}
