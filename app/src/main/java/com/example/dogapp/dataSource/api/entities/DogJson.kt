package com.example.dogapp.dataSource.api.entities

import com.example.dogapp.domain.models.Dog
import com.google.gson.annotations.SerializedName


class DogJson(
    @SerializedName("bred_for") val bredFor: String?,
    @SerializedName("breed_group") val breedGroup: String,
    @SerializedName("height") val height: Height,
    @SerializedName("id") val id: Int,
    @SerializedName("life_span") val lifeSpan: String,
    @SerializedName("name") val name : String,
    @SerializedName("origin") val origin: String,
    @SerializedName("temperament") val temperament: String,
    @SerializedName("weight") val weight: Weight,
    @SerializedName("url") val url:String
    ) {
    class Height(
        @SerializedName("imperial")
        val imperial: String,
        @SerializedName("metrics")
        val metrics: String
    )
    class Weight(
        @SerializedName("imperial")
        val imperial: String,
        @SerializedName("metrics")
        val metrics: String
    )
    fun toDog(): Dog {
        return Dog(id = id, name = name, bredFor = bredFor, lifeSpan = lifeSpan, origin = origin, url = url)
    }
}