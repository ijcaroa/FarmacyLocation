package com.example.farmacylocation.remoto

import retrofit2.Response
import retrofit2.http.GET

interface FarmacyAPI {

    @GET("getLocalesTurnos")
    suspend fun fetchLocalesList() : Response<List<FarmacyData>>

    @GET("getLocalesTurnos")
    suspend fun fetchNameLocal() : Response<FarmacyData>

}