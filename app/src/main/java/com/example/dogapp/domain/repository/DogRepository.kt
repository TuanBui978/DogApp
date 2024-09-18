package com.example.dogapp.domain.repository

import com.example.dogapp.core.result.InternetResult
import com.example.dogapp.domain.service.DogApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepository(private val dogApiService: DogApiService,private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    suspend fun getAllDog() = withContext(dispatcher){
        when (val result = dogApiService.getAllDog()) {
            is InternetResult.Success -> {
                result.data.map { it.toDog() }
            }
            is InternetResult.Failed -> {
                throw result.exception
            }
        }
    }
}