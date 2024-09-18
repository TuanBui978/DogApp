package com.example.dogapp.dataSource.api.apiInterface

import com.example.dogapp.dataSource.api.entities.DogJson
import com.example.dogapp.domain.models.Dog
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApi {
    @GET("dogs.json?fbclid=IwY2xjawFXpoZleHRuA2FlbQIxMAABHfWOn2gGM2029vuSbTjWDaIrb7wL3EfV0Uub5lljkAkDwbq9o3H6XHMwBg_aem_dqcHXvLWiSl-ssM1zkX0Qg")
    suspend fun getDogList(): Response<List<DogJson>>

    companion object {
        private var dogApi: DogApi? = null
        fun getInstance(): DogApi {
            if (dogApi == null) {
                dogApi = Retrofit
                    .Builder()
                    .baseUrl("https://raw.githubusercontent.com/DevTides/DogsApi/master/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DogApi::class.java)
            }
            return dogApi!!
        }
    }
}

