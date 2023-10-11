package com.andreisingeleytsev.differencesapp.ui.utils

sealed class UIEvent{
    object PopBackStack: UIEvent()
    data class Navigate(val route: String): UIEvent()
    object ChangePlayer: UIEvent()
    data class NavigatePopBack(val route: String, val disableRoute: String): UIEvent()
}
