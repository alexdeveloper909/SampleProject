package com.alex.interviewproject.framework.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alex.interviewproject.framework.db.entites.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Insert
    suspend fun insertCity(cityEntity: CityEntity)

    @Query("SELECT * FROM City")
    fun getAllCities() : Flow<List<CityEntity>>
}