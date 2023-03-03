package com.game.gamepad.core.presentation.ui.hub.components.navigation.navigationbar.items

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.game.gamepad.R
import com.game.gamepad.core.presentation.ui.hub.components.navigation.destinations.Destinations

enum class BottomItems(
    val route: String,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
) {
    Home(
        route = Destinations.Home.route,
        icon = Icons.Filled.Home,
        contentDescription = R.string.home
    ),
    Search(
        route = Destinations.Search.route,
        icon = Icons.Filled.Search,
        contentDescription = R.string.search
    )
}
