package com.andreisingeleytsev.differencesapp.ui.screens.game_screen

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrei_singeleytsev.sportquizapp.presentation.dialog.DialogController
import com.andrei_singeleytsev.sportquizapp.presentation.dialog.DialogEvent
import com.andreisingeleytsev.differencesapp.data.entity.DifPictureItem
import com.andreisingeleytsev.differencesapp.data.repository.DifPictureItemRepository
import com.andreisingeleytsev.differencesapp.ui.screens.level_screen.LevelScreenEvent
import com.andreisingeleytsev.differencesapp.ui.utils.Routes
import com.andreisingeleytsev.differencesapp.ui.utils.TimeUtils
import com.andreisingeleytsev.differencesapp.ui.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val difPictureItemRepository: DifPictureItemRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel(),DialogController {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private fun sendUIEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
    override var dialogTitle = mutableStateOf("Find 5 differences!")
        private set
    override var openDialog = mutableStateOf(false)
        private set

    override fun onDialogEvent(event: DialogEvent) {
            when(event){
                is DialogEvent.OnCancel->{
                    openDialog.value = false
                    timer.start()
                }
                else -> {

                }
            }
    }

    fun onEvent(event: GameScreenEvent){
        when(event) {
            is GameScreenEvent.OnPause -> {
                timer.cancel()
                openDialog.value = true
            }
            is GameScreenEvent.OnFinish -> {
                viewModelScope.launch {
                    val time = TimeUtils.getTime(time.value)
                    difPictureItemRepository.insertItem(
                        mainPicture.value!!.copy(bestTime = time)
                    )
                    try {
                        val id = mainPicture.value!!.id!! + 1
                        val nextPicture =
                            difPictureItemRepository.getItemByID(id)
                        difPictureItemRepository.insertItem(nextPicture.copy(isDisabled = false))
                        Log.d("tag", nextPicture.toString())
                    } catch (_: NullPointerException) {

                    }
                    sendUIEvent(
                        UIEvent.NavigatePopBack(
                            Routes.FINISH_SCREEN
                                    + "/${mainPicture.value!!.id}",
                            Routes.GAME_SCREEN + "/{picture_id}"
                        )
                    )
                }
            }

            is GameScreenEvent.OnBackToMenu -> {
                openDialog.value=false
                sendUIEvent(
                    UIEvent.NavigatePopBack(
                        Routes.MENU_SCREEN,
                        Routes.MENU_SCREEN
                    )
                )
            }

            is GameScreenEvent.OnRestart -> {
                time.value = 0L
                openDialog.value = false
            }
            is GameScreenEvent.OnReturn -> {
                sendUIEvent(UIEvent.PopBackStack)
            }
        }
    }
    val mainPicture = mutableStateOf<DifPictureItem?>(
        null
    )

    val time = mutableStateOf(
        0L
    )
    private val timer = object : CountDownTimer(100000000, 1000){
        override fun onTick(p0: Long) {
            time.value = time.value+1000L
        }

        override fun onFinish() {

        }

    }
    private var noteID = 1
    init {
        noteID = savedStateHandle.get<String>("picture_id")?.toInt()!!
        viewModelScope.launch {
            mainPicture.value = difPictureItemRepository.getItemByID(noteID)
            timer.start()
        }
    }
}