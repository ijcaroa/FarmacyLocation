package com.example.farmacylocation

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
        viewModelScope.launch {
          repository.fetchNameFarmacy()
        }
    }
    fun getFarmacyList() : LiveData<List<FarmacyEntity>> =
            repository.farmacyListLiveData

    fun getLocal() : LiveData<FarmacyEntity> = repository.farmacyNameLiveData

}