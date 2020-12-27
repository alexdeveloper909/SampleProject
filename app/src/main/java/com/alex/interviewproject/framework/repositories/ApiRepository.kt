package com.alex.interviewproject.framework.repositories

import android.content.Context
import com.alex.interviewproject.framework.network.ApiClient
import com.alex.interviewproject.framework.network.Resource
import com.alex.interviewproject.framework.network.ResponseHandler
import com.alex.interviewproject.framework.network.response.ApiGetCitiesResponse
import com.alex.interviewproject.framework.repositories.contracts.ApiRepositoryContract
import java.lang.Exception

class ApiRepository(
    private val apiClient:ApiClient,
    private val responseHandler:ResponseHandler,
    private val context: Context
) :ApiRepositoryContract{
    override suspend fun fetchCitiesData(): Resource<ApiGetCitiesResponse> {
        return try{
            responseHandler.handleSuccess(apiClient.getApiService(context).getCitiesData())
        }catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}