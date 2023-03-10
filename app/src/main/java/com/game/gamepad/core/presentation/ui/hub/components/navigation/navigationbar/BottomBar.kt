package com.game.gamepad.core.presentation.ui.hub.components.navigation.navigationbar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.game.gamepad.core.presentation.ui.hub.components.navigation.navigationbar.items.BottomItems

@Composable
fun BottomBar(
    modifier: Modifier,
    curRoute: String,
    onNavigate: (String) -> Unit,
    onPopBackStack: () -> Unit
) {
    NavigationBar(
        modifier = modifier
    ) {
        BottomItems.values().forEach { bottomItems ->
            NavigationBarItem(
                selected = curRoute.startsWith(bottomItems.route),
                onClick = {
                    if (curRoute != bottomItems.route) {
                        onPopBackStack()
                        onNavigate(bottomItems.route)
                    }
                },
                icon = {
                    Icon(
                        imageVector = bottomItems.icon,
                        contentDescription = stringResource(id = bottomItems.contentDescription)
                    )
                }
            )
        }
    }
}
