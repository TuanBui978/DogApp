package com.example.dogapp.domain.service

import com.example.dogapp.core.result.InternetResult
import com.example.dogapp.dataSource.api.apiInterface.DogApi
import com.example.dogapp.dataSource.api.entities.DogJson

class DogApiService: BaseService() {
    suspend fun getAllDog(): InternetResult<List<DogJson>> {
        return callApi { DogApi.getInstance().getDogList() }
    }
}