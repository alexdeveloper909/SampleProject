package com.alex.interviewproject.framework.db.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "City")
data class CityEntity (
    val name:String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}