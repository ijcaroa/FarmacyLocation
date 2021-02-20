package com.example.farmacylocation.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.farmacylocation.fromInternetToEntity
import com.example.farmacylocation.local.FarmacyEntity
import com.example.farmacylocation.local.FarmacyDao
import com.example.farmacylocation.remoto.FarmacyData
import com.example.farmacylocation.remoto.RetrofitClient

class FarmacyRepository (private val farmacyDao: FarmacyDao) {

    private val retrofitClient = RetrofitClient.getRetrofit()


    //Esto no me sirve
    val farmacyNameLiveData = farmacyDao.getNameFarmacy()

    //Esto pasa la lista de comunas ordenadas por region a la base de datos
// y lo trae convetido en una lista de objetos
    val farmacyListLiveData = farmacyDao.getAllFarmacyByRegion()
    suspend fun fetchListFarmacy() {
        val service = kotlin.runCatching { retrofitClient.fetchLocalesList() }
        service.onSuccess {
            when (it.code()) {
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

    fun converterFromInternet(farmacyData: List<FarmacyData>): List<FarmacyEntity> {
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

//Metodo que recibe la comuna y guarda los nombres de farmacias
// de esa comuna en la base de datos

    suspend fun fetchFarmacyByComuna(id: String, region: String, name: String,
                                     commune: String, address: String, tel: String, lat: String, longi: String) {
        val service = kotlin.runCatching { retrofitClient.fetchLocalComuna(commune) }
        service.onSuccess {
            when (it.code()) {
                200 -> it.body()?.let {
                    farmacyDao.insertAllLocal(fromInternetToEntity
                    (it, id, name, commune, address, tel, lat, longi, region))
                }
                else -> Log.d("REPO", "${it.code()} - ${it.errorBody()}")
            }

        }
        service.onFailure {
            Log.e("REPOS", "${it.message}")
        }
    }

        fun getAllFarmacybyComuna (commune: String): LiveData<List<FarmacyEntity>>{
  return farmacyDao.getFarmacybyName(commune)
        }

}



