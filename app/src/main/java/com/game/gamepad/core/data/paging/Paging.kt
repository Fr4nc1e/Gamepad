package com.game.gamepad.core.data.paging

interface Paging<Page, Item> {
    suspend fun loadNextItems()
    fun reset()
}
