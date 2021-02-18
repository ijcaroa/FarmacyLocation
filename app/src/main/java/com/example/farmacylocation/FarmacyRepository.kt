package com.example.farmacylocation

import android.util.Log
import com.example.farmacylocation.local.FarmacyEntity
import com.example.farmacylocation.local.FarmacyDao
import com.example.farmacylocation.remoto.FarmacyData
import com.example.farmacylocation.remoto.RetrofitClient



class FarmacyRepository (private val farmacyDao: FarmacyDao) {

    private val retrofitClient = RetrofitClient.getRetrofit()
    val farmacyListLiveData = farmacyDao.getAllFarmacyByRegion()

    suspend fun fetchListFarmacy() {
    val service = kotlin.runCatching {retrofitClient.fetchLocalesList()}
        service.onSuccess {
            when(it.code()) {
                200 -> it.body()?.let {
                  farmacyDao.insertAllLocal(converterFromInternet(it))
                }
                else -> Log.d("REPO", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }

    }

    fun converterFromInternet(farmacyData: List<FarmacyData>) : List<FarmacyEntity>{
        val listFarmacyEntity = mutableListOf<FarmacyEntity>()
            farmacyData.map {
                listFarmacyEntity.add(FarmacyEntity(region = it.region,
                        id = it.id,
                        name = it.nombre,
                        commune = it.comuna,
                        address = it.direccion,
                        tel = it.telefono,
                        lat = it.latitud,
                        longi = it.longitud))
            }
        return listFarmacyEntity
    }

}