package com.andreisingeleytsev.differencesapp.ui.screens.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.differencesapp.ui.screens.menu_screen.MenuScreenEvent
import com.andreisingeleytsev.differencesapp.ui.utils.Routes
import com.andreisingeleytsev.differencesapp.ui.utils.UIEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SettingsScreenViewModel: ViewModel() {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private fun sendUIEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun onEvent(event: SettingsScreenEvent){
        when(event) {
            is SettingsScreenEvent.OnSoundPressed -> {
                sendUIEvent(UIEvent.ChangePlayer)
            }
        }
    }
}