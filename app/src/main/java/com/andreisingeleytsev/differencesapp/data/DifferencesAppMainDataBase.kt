package com.andreisingeleytsev.differencesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreisingeleytsev.differencesapp.data.dao.DifPictureItemDao
import com.andreisingeleytsev.differencesapp.data.entity.DifPictureItem

@Database(
    entities = [DifPictureItem::class],
    version = 1
)
abstract class DifferencesAppMainDataBase: RoomDatabase() {
    abstract val difPictureItemDao: DifPictureItemDao
}