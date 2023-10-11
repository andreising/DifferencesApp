package com.andreisingeleytsev.differencesapp.di

import android.app.Application

import androidx.room.Room
import com.andreisingeleytsev.differencesapp.data.DifferencesAppMainDataBase
import com.andreisingeleytsev.differencesapp.data.repository.DifPictureItemRepository
import com.andreisingeleytsev.differencesapp.data.repository.implementations.DifPictureItemRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DifferencesAppModule {
    @Provides
    @Singleton
    fun provideMainDatabase(app: Application): DifferencesAppMainDataBase {
        return Room.databaseBuilder(
            app,
            DifferencesAppMainDataBase::class.java,
            "diff_app_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDiffPictureRepository(db: DifferencesAppMainDataBase): DifPictureItemRepository {
        return DifPictureItemRepositoryImplementation(db.difPictureItemDao)
    }

}