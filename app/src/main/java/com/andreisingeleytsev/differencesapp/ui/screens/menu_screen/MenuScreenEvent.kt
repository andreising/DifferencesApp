package com.andreisingeleytsev.differencesapp.ui.screens.menu_screen

sealed class MenuScreenEvent {
    object OnPlayPressed: MenuScreenEvent()
    object OnSettingsPressed: MenuScreenEvent()
    object OnRulesPressed: MenuScreenEvent()
}

