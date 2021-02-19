package com.example.farmacylocation.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.farmacylocation.local.FarmacyEntity
import com.example.farmacylocation.local.FarmacyDataBase
import kotlinx.coroutines.launch

class FarmacyViewModel (application: Application) : AndroidViewModel(application) {

    private val repository : FarmacyRepository

    init {
        val db = FarmacyDataBase.getDataBase(application)
        val farmacyDao = db.farmacyDao()
        repository = FarmacyRepository(farmacyDao)

        viewModelScope.launch {
            repository.fetchListFarmacy()
        }

    }
    // Para la lista de farmacias por comunas
    fun getFarmacyList() : LiveData<List<FarmacyEntity>> =
            repository.farmacyListLiveData

    // crear función para pasar las farmacias por nombre de una comuna


   

    private var comunaSelected : String = ""

    fun getNameFarmacy(comuna : String) = viewModelScope.launch {
        comunaSelected = comuna
        repository.fetchListFarmacy()

    }


}