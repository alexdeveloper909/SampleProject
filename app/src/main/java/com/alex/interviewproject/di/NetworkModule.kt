package com.alex.interviewproject.di

import android.content.Context
import com.alex.interviewproject.framework.network.ApiClient
import com.alex.interviewproject.framework.network.ResponseHandler
import com.alex.interviewproject.framework.repositories.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideApiClient(): ApiClient {
        return ApiClient
    }

    @Provides
    fun provideResponseHandler() : ResponseHandler = ResponseHandler()

    @Provides
    fun provideApiRepository(
        apiClient: ApiClient,
        responseHandler: ResponseHandler,
        @ApplicationContext context: Context
    ): ApiRepository {
        return ApiRepository(apiClient, responseHandler, context)
    }
}