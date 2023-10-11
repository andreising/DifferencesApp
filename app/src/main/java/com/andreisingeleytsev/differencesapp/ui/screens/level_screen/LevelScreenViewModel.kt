package com.andreisingeleytsev.differencesapp.ui.screens.level_screen

import android.graphics.Picture
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.differencesapp.R
import com.andreisingeleytsev.differencesapp.data.entity.DifPictureItem
import com.andreisingeleytsev.differencesapp.data.repository.DifPictureItemRepository
import com.andreisingeleytsev.differencesapp.ui.screens.menu_screen.MenuScreenEvent
import com.andreisingeleytsev.differencesapp.ui.utils.Routes
import com.andreisingeleytsev.differencesapp.ui.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelScreenViewModel @Inject constructor(
    private val difPictureItemRepository: DifPictureItemRepository
): ViewModel() {
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private fun sendUIEvent(event: UIEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun onEvent(event: LevelScreenEvent){
        when(event) {
            is LevelScreenEvent.OnPictureChose -> {
                val item = event.difPictureItem
                if (!item.isDisabled) {
                    sendUIEvent(UIEvent.Navigate(Routes.GAME_SCREEN + "/${item.id}"))
                }
            }
        }
    }
    val difPictureItemList = difPictureItemRepository.getAllItems()
    private suspend fun isFirstLaunch(): Boolean{
        return difPictureItemList.first().isEmpty()
    }
    init {
        viewModelScope.launch {
            if (isFirstLaunch()) {
                PictureList.list.forEach {pair ->
                    difPictureItemRepository.insertItem(
                        DifPictureItem(
                            id = null,
                            drawableFirstId = pair.first,
                            drawableSecondId = pair.second,
                            isDisabled = PictureList.list.indexOf(pair)!=0
                        )
                    )

                }
            }
        }
    }

    object PictureList{
        val list = listOf(
            Pair(
                R.drawable.picture_1_1,
                R.drawable.picture_1_2
            ),
            Pair(
                R.drawable.picture_2_1,
                R.drawable.picture_2_2
            ),
            Pair(
                R.drawable.picture_3_1,
                R.drawable.picture_3_2
            ),
            Pair(
                R.drawable.picture_4_1,
                R.drawable.picture_4_2
            ),
            Pair(
                R.drawable.picture_5_1,
                R.drawable.picture_5_2
            ),
            Pair(
                R.drawable.picture_6_1,
                R.drawable.picture_6_2
            ),
            Pair(
                R.drawable.picture_7_1,
                R.drawable.picture_7_2
            ),
            Pair(
                R.drawable.picture_8_1,
                R.drawable.picture_8_2
            ),
        )
    }
}
