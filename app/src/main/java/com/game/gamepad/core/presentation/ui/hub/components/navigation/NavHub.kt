package com.game.gamepad.core.presentation.ui.hub.components.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.game.gamepad.core.presentation.ui.hub.components.navigation.destinations.Destinations
import com.game.gamepad.feature.detail.DetailScreen
import com.game.gamepad.feature.home.presentation.ui.HomeScreen
import com.game.gamepad.feature.search.SearchScreen

@Composable
fun NavHub(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Destinations.Home.route
    ) {
        composable(Destinations.Home.route) {
            HomeScreen(
                modifier = modifier,
                snackbarHostState = snackbarHostState,
                onNavigate = navHostController::navigate
            )
        }
        composable(Destinations.Search.route) {
            SearchScreen(
                modifier = modifier,
                snackbarHostState = snackbarHostState,
                onNavigate = navHostController::navigate
            )
        }
        composable(
            route = Destinations.Detail.route + "/{gameId}",
            arguments = listOf(
                navArgument("gameId") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(
                modifier = modifier,
                snackbarHostState = snackbarHostState,
                onNavigate = navHostController::navigate
            )
        }
    }
}
