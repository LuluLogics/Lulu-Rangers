package com.lulu_ranger.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lulu_ranger.ui.screens.PlayerListScreen
import com.lulu_ranger.ui.screens.FavouritesScreen
import com.lulu_ranger.ui.screens.SplashScreen
import com.lulu_ranger.ui.screens.SoccerFormationScreen
import com.lulu_ranger.ui.screens.TeamAchievementsScreen
import com.lulu_ranger.viewmodel.FavouriteViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    val favouriteViewModel: FavouriteViewModel = viewModel()

    NavHost(navController, startDestination = "splash") {

        composable("splash") {
            AnimatedScreenTransition {
                SplashScreen(navController = navController)
            }
        }

        composable("player_list") {
            AnimatedScreenTransition {
                PlayerListScreen(navController, favouriteViewModel = favouriteViewModel)
            }
        }

        composable("favourites") {
            AnimatedScreenTransition {
                FavouritesScreen(navController, favouriteViewModel = favouriteViewModel)
            }
        }

        composable("team_achievements") {
            AnimatedScreenTransition {
                TeamAchievementsScreen(navController)
            }
        }

        composable("soccer_formation") {
            AnimatedScreenTransition {
                SoccerFormationScreen(navController)
            }
        }
    }
}
                                                                                                                                                                                                                                                     @OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedScreenTransition(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(500)) + slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth / 2 },
            animationSpec = tween(500)
        ),
        exit = fadeOut(animationSpec = tween(500)) + slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth / 2 },
            animationSpec = tween(500)
        )
    ) {
        content()
    }
}
