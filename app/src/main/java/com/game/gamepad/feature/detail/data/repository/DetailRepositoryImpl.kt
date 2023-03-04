package com.game.gamepad.feature.detail.data.repository

import com.game.gamepad.R
import com.game.gamepad.core.data.util.Resource
import com.game.gamepad.core.util.UiText
import com.game.gamepad.feature.detail.data.api.DetailApi
import com.game.gamepad.feature.detail.domain.models.Details
import com.game.gamepad.feature.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class DetailRepositoryImpl(
    private val api: DetailApi
) : DetailRepository {
    override fun getDetail(gameId: String): Flow<Resource<Details?>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val items = api.getGameDetail(gameId).toDetails()
                emit(Resource.Success(items))
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        data = null,
                        uiText = UiText.StringResource(R.string.fail_to_connect)
                    )
                )
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        data = null,
                        uiText = UiText.StringResource(R.string.fail_to_connect)
                    )
                )
            }
            emit(Resource.Loading(false))
        }
    }
}