package com.example.dogapp.core.result

import java.lang.Exception

sealed class InternetResult<out T> {
    data class Success<T>(val data: T) : InternetResult<T>()
    data class Failed(val exception: Exception): InternetResult<Nothing>()
}