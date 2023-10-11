package com.andreisingeleytsev.differencesapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dif_item")
data class DifPictureItem(
    @PrimaryKey
    val id: Int?,
    val drawableFirstId: Int,
    val drawableSecondId: Int,
    val isDisabled: Boolean = true,
    val bestTime: String = "0:00"
)
