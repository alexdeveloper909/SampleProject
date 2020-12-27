package com.alex.interviewproject.framework.repositories

import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.LargeTest
import com.alex.interviewproject.framework.network.ApiClient
import com.alex.interviewproject.framework.network.ResponseHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

@ExperimentalCoroutinesApi
@LargeTest
class ApiRepositoryTest {

    lateinit var apiClient: ApiClient
    lateinit var responseHandler: ResponseHandler
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        apiClient = ApiClient
        responseHandler = ResponseHandler()
        apiRepository = ApiRepository(apiClient,responseHandler,ApplicationProvider.getApplicationContext())
    }

    @LargeTest
    @Test
    fun fetchCitiesData() = runBlocking{
        val list = apiRepository.fetchCitiesData()

        val firstInListToponym = "Kyiv"
        val actualFirstInListToponym = list.data!!.geonames.first().toponymName

        assertEquals(firstInListToponym,actualFirstInListToponym)
    }
}