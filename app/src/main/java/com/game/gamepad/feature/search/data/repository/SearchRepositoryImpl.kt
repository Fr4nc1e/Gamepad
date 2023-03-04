package com.game.gamepad.feature.search.data.repository

import com.game.gamepad.R
import com.game.gamepad.core.data.util.Resource
import com.game.gamepad.core.util.UiText
import com.game.gamepad.feature.search.data.api.SearchApi
import com.game.gamepad.feature.search.domain.models.SearchItem
import com.game.gamepad.feature.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class SearchRepositoryImpl(
    private val api: SearchApi
): SearchRepository {
    override fun searchGame(gameName: String): Flow<Resource<List<SearchItem>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val items = api.searchGames(gameName).toSearchResults().searchItems
                emit(Resource.Success(items))
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
            emit(Resource.Loading(false))
        }
    }
}