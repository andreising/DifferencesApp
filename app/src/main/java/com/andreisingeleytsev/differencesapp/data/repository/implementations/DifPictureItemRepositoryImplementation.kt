package com.andreisingeleytsev.differencesapp.data.repository.implementations

import android.util.Log
import com.andreisingeleytsev.differencesapp.data.dao.DifPictureItemDao
import com.andreisingeleytsev.differencesapp.data.entity.DifPictureItem
import com.andreisingeleytsev.differencesapp.data.repository.DifPictureItemRepository
import kotlinx.coroutines.flow.Flow

class DifPictureItemRepositoryImplementation(private val dao: DifPictureItemDao) : DifPictureItemRepository {
    override fun getAllItems(): Flow<List<DifPictureItem>> {
        return dao.getItems()
    }

    override suspend fun insertItem(difPictureItem: DifPictureItem) {
        dao.insertItem(difPictureItem)
    }

    override suspend fun getItemByID(id: Int): DifPictureItem {
        return dao.getNoteByID(id)
    }

}