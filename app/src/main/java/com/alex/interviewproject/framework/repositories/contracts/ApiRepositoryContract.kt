package com.alex.interviewproject.framework.repositories.contracts

import com.alex.interviewproject.framework.network.Resource
import com.alex.interviewproject.framework.network.response.ApiGetCitiesResponse

interface ApiRepositoryContract {
    suspend fun fetchCitiesData(): Resource<ApiGetCitiesResponse>
}