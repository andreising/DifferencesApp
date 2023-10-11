package com.andreisingeleytsev.differencesapp.ui.screens.menu_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.differencesapp.ui.utils.Routes
import com.andreisingeleytsev.differencesapp.ui.utils.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MenuScreenViewModel: ViewModel() {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private fun sendUIEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun onEvent(event: MenuScreenEvent){
        when(event) {
            is MenuScreenEvent.OnPlayPressed -> {
                sendUIEvent(UIEvent.Navigate(Routes.LEVEL_SCREEN))
            }
            is MenuScreenEvent.OnSettingsPressed -> {
                sendUIEvent(UIEvent.Navigate(Routes.SETTINGS_SCREEN))
            }
            is MenuScreenEvent.OnRulesPressed -> {
                sendUIEvent(UIEvent.Navigate(Routes.RULES_SCREEN))
            }
        }

    }
}