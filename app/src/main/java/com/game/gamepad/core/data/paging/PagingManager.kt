package com.game.gamepad.core.data.paging

import com.game.gamepad.core.data.util.Resource
import kotlinx.coroutines.flow.Flow

class PagingManager<Page, Item>(
    private val initialPage: Page,
    private inline val onLoadUpdated: suspend (Boolean) -> Unit,
    private inline val onRequest: (nextPage: Page) -> Flow<Resource<List<Item>>>,
    private inline val onSuccess: suspend (items: Flow<Resource<List<Item>>>) -> Unit
) : Paging<Page, Item> {
    var currentPage = initialPage
    private var isMakingRequest = false
    override suspend fun loadNextItems() {
        if (isMakingRequest) return
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentPage)
        isMakingRequest = false
        onSuccess(result)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentPage = initialPage
    }
}
