package com.game.gamepad.core.presentation.ui.hub.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class AppHubViewModel @Inject constructor() : ViewModel() {
    private val _curRoute = MutableStateFlow("")
    val curRoute = _curRoute.asStateFlow()

    private val showBottomBarList = listOf("route_home", "route_search")

    fun getCurRoute(route: String) {
        _curRoute.update { route }
    }

    fun inList(): Boolean {
        return (_curRoute.value in showBottomBarList)
    }
}
