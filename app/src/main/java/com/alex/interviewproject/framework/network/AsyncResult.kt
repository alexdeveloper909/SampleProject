package com.alex.interviewproject.framework.network

sealed class AsyncResult<out T> {
    object InFlight : AsyncResult<Nothing>()
    data class Error(val errorMessage: String) : AsyncResult<Nothing>()
    data class Loaded<out T>(val result: T) : AsyncResult<T>()
}