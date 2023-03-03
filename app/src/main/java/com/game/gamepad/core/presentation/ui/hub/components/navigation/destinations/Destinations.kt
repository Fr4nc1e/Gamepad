package com.game.gamepad.core.presentation.ui.hub.components.navigation.destinations

sealed class Destinations(val route: String) {
    object Home : Destinations("route_home")
    object Detail : Destinations("route_detail")
    object Search : Destinations("route_search")
}
