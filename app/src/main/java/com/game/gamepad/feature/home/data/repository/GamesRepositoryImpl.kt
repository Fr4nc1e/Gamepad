package com.game.gamepad.feature.home.data.repository

import com.game.gamepad.R
import com.game.gamepad.core.data.util.Resource
import com.game.gamepad.core.util.UiText
import com.game.gamepad.feature.home.data.api.GamesApi
import com.game.gamepad.feature.home.domain.models.Game
import com.game.gamepad.feature.home.domain.repository.GamesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class GamesRepositoryImpl(
    private val api: GamesApi
) : GamesRepository {
    override fun getGames(page: Int, pageSize: Int): Flow<Resource<List<Game>>> {
        return flow {
            delay(2000L)
            try {
                val gamesList = api.getGames(page, pageSize).toGamesList().games
                emit(Resource.Success(data = gamesList))
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        data = emptyList(),
                        uiText = UiText.StringResource(R.string.fail_to_connect)
                    )
                )
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        data = emptyList(),
                        uiText = UiText.StringResource(R.string.fail_to_connect)
                    )
                )
            }
        }
    }
}
