package com.alex.interviewproject.framework.network.response

data class CitiesResponse(
    val lng:String,
    val geonameId:Int,
    val countryCode:String,
    val name:String,
    val toponymName:String,
    val lat:String,
    val fcl:String,
    val fcode:String
)