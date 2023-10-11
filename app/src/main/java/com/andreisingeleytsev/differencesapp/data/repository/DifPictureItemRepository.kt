package com.andreisingeleytsev.differencesapp.data.repository

import com.andreisingeleytsev.differencesapp.data.entity.DifPictureItem
import kotlinx.coroutines.flow.Flow


interface DifPictureItemRepository {
    fun getAllItems(): Flow<List<DifPictureItem>>
    suspend fun insertItem(difPictureItem: DifPictureItem)
    suspend fun getItemByID(id: Int): DifPictureItem
}