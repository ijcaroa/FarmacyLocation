package com.example.farmacylocation.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FarmacyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocal(localList: List<FarmacyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocal(local: FarmacyEntity)

    @Update
    suspend fun updateLocalFav(localFav: FarmacyEntity)

 /*   @Query("SELECT region FROM farmacy_table ORDER BY region DESC")
    fun getAllFarmacyByRegion(): LiveData<List<FarmacyEntity>>*/


  //Esta me trae todas las comunas ordenadas por región
    @Query("SELECT * FROM farmacy_table GROUP BY commune ORDER BY region ASC")
    fun getAllFarmacyByRegion(): LiveData<List<FarmacyEntity>>


    @Query("SELECT * FROM farmacy_table ORDER BY name  ")
    fun getNameFarmacy(): LiveData<List<FarmacyEntity>>

    @Query("SELECT * FROM farmacy_table WHERE fav = 1")
    fun getAllFavLocal(): LiveData<List<FarmacyEntity>>


   //esta me va a traer las farmacias por nombre de una comuna que seleccione
    @Query("SELECT * FROM farmacy_table WHERE commune = :comuna")
    fun getFarmacybyName (comuna: String): LiveData<List<FarmacyEntity>>

    @Query("SELECT * FROM farmacy_table WHERE id = :idFarmacy")
    fun getFarmacyById (idFarmacy:String): LiveData<FarmacyEntity>

}