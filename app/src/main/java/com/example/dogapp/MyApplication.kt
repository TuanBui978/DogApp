package com.example.dogapp

import android.app.Application
import com.example.dogapp.domain.repository.DogRepository
import com.example.dogapp.domain.service.DogApiService

class MyApplication: Application() {
    private val _dogRepository: DogRepository = DogRepository(DogApiService())
    val dogRepository
        get() = _dogRepository
}