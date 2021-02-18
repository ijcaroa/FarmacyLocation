package com.example.farmacylocation.remoto

import com.google.gson.annotations.SerializedName

data class FarmacyData(
        @SerializedName("fk_region")
        val region : String,
        @SerializedName("local_id")
        val id : String,
        @SerializedName("local_nombre")
        val nombre : String,
        @SerializedName("comuna_nombre")
        val comuna : String,
        @SerializedName("local_direccion")
        val direccion : String,
        @SerializedName("local_telefono")
        val telefono : String,
        @SerializedName("local_lat")
        val latitud : String,
        @SerializedName("local_lng")
        val longitud : String)
