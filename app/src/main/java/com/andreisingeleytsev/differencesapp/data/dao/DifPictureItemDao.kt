package com.andreisingeleytsev.differencesapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreisingeleytsev.differencesapp.data.entity.DifPictureItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DifPictureItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: DifPictureItem)
    @Query("SELECT * FROM dif_item")
    fun getItems(): Flow<List<DifPictureItem>>
    @Query ("SELECT * FROM dif_item WHERE id = :id")
    suspend fun getNoteByID(id:Int): DifPictureItem
}