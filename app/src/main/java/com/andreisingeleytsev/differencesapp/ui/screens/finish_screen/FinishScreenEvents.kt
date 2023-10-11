package com.andreisingeleytsev.differencesapp.ui.screens.finish_screen

sealed class FinishScreenEvents{
    object OnNext: FinishScreenEvents()
    object ToMenu: FinishScreenEvents()
}
