package com.alex.interviewproject.framework.db.datasource

import com.alex.interviewproject.framework.db.daos.CityDao
import com.alex.interviewproject.framework.db.entites.CityEntity
import com.alex.interviewproject.framework.domain.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityDataSource @Inject constructor(private val cityDao:CityDao){

    suspend fun addCities(cities:List<City>){
        cities.forEach {
            cityDao.insertCity(CityEntity(it.name))
        }
    }

    suspend fun addCity(city:City){
        cityDao.insertCity(CityEntity(city.name))
    }

    fun getCities(): Flow<List<City>> {
        //val i = cityDao.getAllCities().map { City(it.id!!,it.name) }
        return cityDao.getAllCities().map { list ->
            list.map {
                City(it.id!!,it.name)
            }
        }
    }
}