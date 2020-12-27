package com.alex.interviewproject.ui.fetchingdata

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.alex.interviewproject.framework.db.datasource.CityDataSource
import com.alex.interviewproject.framework.domain.City
import com.alex.interviewproject.framework.network.Resource
import com.alex.interviewproject.framework.network.Status
import com.alex.interviewproject.framework.repositories.ApiRepository

class FetchDataViewModel @ViewModelInject constructor(
    private val apiRepository: ApiRepository,
    private val cityDataSource: CityDataSource
): ViewModel() {



    val citiesNet : LiveData<Resource<List<City>>> = liveData {
        emit(Resource.loading(null))
        val data = apiRepository.fetchCitiesData()
        when(data.status){
            Status.SUCCESS -> emit(Resource.success(data.data!!.geonames.map { City(it.geonameId,it.name) }))
            Status.ERROR -> emit(Resource.error(data.message!!,null))
        }
    }

    val citiesStorage:LiveData<List<City>> = liveData {
        emitSource(cityDataSource.getCities().asLiveData())
    }



    suspend fun addCity(city:City){
        cityDataSource.addCity(city)
    }



}