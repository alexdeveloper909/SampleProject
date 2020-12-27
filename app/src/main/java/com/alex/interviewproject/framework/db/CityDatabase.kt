package com.alex.interviewproject.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alex.interviewproject.framework.db.daos.CityDao
import com.alex.interviewproject.framework.db.entites.CityEntity

@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CityDatabase : RoomDatabase(){

    abstract fun cityDao(): CityDao
}