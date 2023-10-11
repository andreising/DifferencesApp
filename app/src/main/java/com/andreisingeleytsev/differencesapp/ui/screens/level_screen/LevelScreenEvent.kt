package com.andreisingeleytsev.differencesapp.ui.screens.level_screen

import com.andreisingeleytsev.differencesapp.data.entity.DifPictureItem

sealed class LevelScreenEvent{
    data class OnPictureChose(val difPictureItem: DifPictureItem):LevelScreenEvent()
}
