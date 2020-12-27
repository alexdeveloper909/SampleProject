package com.alex.interviewproject.framework.domain

data class City(
    val id:Int,
    val name:String
){
    override fun toString(): String {
        return name
    }
}