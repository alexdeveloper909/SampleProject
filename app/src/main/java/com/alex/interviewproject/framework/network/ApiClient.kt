package com.alex.interviewproject.framework.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private lateinit var apiService: ApiService

    fun getApiService(context: Context): ApiService {
        // Initialize ApiService if not initialized yet
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //.client(okhttpClient())
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService
    }

    /*private fun okhttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .build()
    }*/
}