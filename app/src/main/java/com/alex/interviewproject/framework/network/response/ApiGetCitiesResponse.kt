package com.alex.interviewproject.framework.network.response

data class ApiGetCitiesResponse(
    val totalResultCount:Int,
    val geonames:List<CitiesResponse>
)