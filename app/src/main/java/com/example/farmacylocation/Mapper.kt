package com.example.farmacylocation

import com.example.farmacylocation.local.FarmacyEntity
import com.example.farmacylocation.remoto.FarmacyData



fun fromInternetToEntity (farmacyData: FarmacyData, id: String, region:String, name:String,
                            commune: String, address:String, tel:String, lat: String, longi:String)
: List<FarmacyEntity>{ return farmacyData.comuna.map { FarmacyEntity(id = id, region = region,name = name,
            commune = it.toString(), address = address, tel = tel, lat = lat, longi = longi ) }
}