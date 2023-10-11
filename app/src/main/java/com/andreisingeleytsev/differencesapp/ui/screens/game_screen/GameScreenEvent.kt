package com.andreisingeleytsev.differencesapp.ui.screens.game_screen

sealed class GameScreenEvent{
    object OnPause: GameScreenEvent()
    object OnFinish: GameScreenEvent()
    object OnRestart: GameScreenEvent()
    object OnReturn: GameScreenEvent()
    object OnBackToMenu: GameScreenEvent()
}
