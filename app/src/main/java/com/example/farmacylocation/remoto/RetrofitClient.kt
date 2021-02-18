package com.example.farmacylocation.remoto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        private const val BASE_URL = "http://farmanet.minsal.cl/index.php/ws/"

    fun getRetrofit() : FarmacyAPI {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(FarmacyAPI::class.java)

    }
    }
}