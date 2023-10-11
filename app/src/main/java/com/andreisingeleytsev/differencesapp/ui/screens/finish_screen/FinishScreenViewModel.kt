package com.andreisingeleytsev.differencesapp.ui.screens.finish_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.differencesapp.data.entity.DifPictureItem
import com.andreisingeleytsev.differencesapp.data.repository.DifPictureItemRepository
import com.andreisingeleytsev.differencesapp.ui.screens.game_screen.GameScreenEvent
import com.andreisingeleytsev.differencesapp.ui.utils.Routes
import com.andreisingeleytsev.differencesapp.ui.utils.TimeUtils
import com.andreisingeleytsev.differencesapp.ui.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinishScreenViewModel @Inject constructor(
    private val difPictureItemRepository: DifPictureItemRepository,
    savedStateHandle: SavedStateHandle,

    ): ViewModel() {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private fun sendUIEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
    fun onEvent(event: FinishScreenEvents){
        when(event) {
            is FinishScreenEvents.OnNext -> {
                sendUIEvent(UIEvent.NavigatePopBack(
                    Routes.GAME_SCREEN + "/${mainPicture.value!!.id!!+1}",
                    Routes.FINISH_SCREEN
                ))
            }
            is FinishScreenEvents.ToMenu -> {
                sendUIEvent(UIEvent.NavigatePopBack(
                    Routes.MENU_SCREEN,
                    Routes.FINISH_SCREEN
                ))
            }
        }
    }
    private val mainPicture = mutableStateOf<DifPictureItem?>(
        null
    )
    val time = mutableStateOf("")
    init {
        val noteID = savedStateHandle.get<String>("picture_id")?.toInt()!!
        viewModelScope.launch {
            mainPicture.value = difPictureItemRepository.getItemByID(noteID)
            time.value = mainPicture.value!!.bestTime
        }
    }
}