package com.alex.interviewproject.framework.network

import com.alex.interviewproject.framework.network.response.ApiGetCitiesResponse
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.FETCH_CITIES)
    suspend fun getCitiesData() : ApiGetCitiesResponse
}