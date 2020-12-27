package com.alex.interviewproject.di

import android.content.Context
import androidx.room.Room
import com.alex.interviewproject.framework.db.CityDatabase
import com.alex.interviewproject.framework.db.daos.CityDao
import com.alex.interviewproject.framework.db.datasource.CityDataSource
import com.alex.interviewproject.framework.network.ApiClient
import com.alex.interviewproject.framework.network.ResponseHandler
import com.alex.interviewproject.framework.repositories.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : CityDatabase {
        return Room.databaseBuilder(
            context,
            CityDatabase::class.java,
            "com.alex.interviewproject.db"
        ).fallbackToDestructiveMigration().allowMainThreadQueries() .build()
    }

    @Provides
    fun provideCityDao(database: CityDatabase) : CityDao {
        return database.cityDao()
    }

    @Provides
    fun provideCityDataSource(cityDao: CityDao): CityDataSource{
        return CityDataSource(cityDao)
    }
}