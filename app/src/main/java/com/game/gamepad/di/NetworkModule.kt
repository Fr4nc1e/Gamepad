package com.game.gamepad.di

import com.game.gamepad.BuildConfig
import com.game.gamepad.core.data.util.ApiConstants
import com.game.gamepad.feature.home.data.api.GamesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val requestBuilder = it.request().newBuilder()
                val originalUrl = it.request().url
                val updatedUrl = originalUrl.newBuilder()
                    .addQueryParameter(ApiConstants.API_KEY, BuildConfig.API_KEY).build()
                requestBuilder.url(updatedUrl)
                it.proceed(requestBuilder.build())
            }
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    @Provides
    @Singleton
    fun provideGamesApi(client: OkHttpClient): GamesApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(GamesApi::class.java)
    }
}
