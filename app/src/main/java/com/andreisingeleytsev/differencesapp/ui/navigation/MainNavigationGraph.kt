package com.andreisingeleytsev.differencesapp.ui.navigation


import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andreisingeleytsev.differencesapp.ui.screens.finish_screen.FinishScreen
import com.andreisingeleytsev.differencesapp.ui.screens.game_screen.GameScreen
import com.andreisingeleytsev.differencesapp.ui.screens.level_screen.LevelsScreen
import com.andreisingeleytsev.differencesapp.ui.screens.menu_screen.MenuScreen
import com.andreisingeleytsev.differencesapp.ui.screens.rules_screen.RulesScreen
import com.andreisingeleytsev.differencesapp.ui.screens.settings_screen.SettingsScreen
import com.andreisingeleytsev.differencesapp.ui.utils.Routes


@Composable
fun MainNavigationGraph(mediaPlayer: MediaPlayer?) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MENU_SCREEN) {

        composable(Routes.MENU_SCREEN) {
            MenuScreen(navController)
        }
        composable(Routes.GAME_SCREEN + "/{picture_id}") {
            GameScreen(navController)
        }
        composable(Routes.LEVEL_SCREEN) {
            LevelsScreen(navController)
        }
        composable(Routes.FINISH_SCREEN + "/{picture_id}") {
            FinishScreen(navController)
        }
        composable(Routes.SETTINGS_SCREEN) {
            SettingsScreen(mediaPlayer = mediaPlayer)
        }
        composable(Routes.RULES_SCREEN) {
            RulesScreen()
        }

    }
}
