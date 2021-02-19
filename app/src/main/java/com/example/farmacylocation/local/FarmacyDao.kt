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

    @Query("SELECT * FROM farmacy_table GROUP BY commune ORDER BY region ASC")
    fun getAllFarmacyByRegion(): LiveData<List<FarmacyEntity>>

    @Query("SELECT * FROM farmacy_table GROUP BY name ORDER BY commune")
    fun getNameFarmacy(): LiveData<List<FarmacyEntity>>

    @Query("SELECT * FROM farmacy_table WHERE fav = 1")
    fun getAllFavLocal(): LiveData<List<FarmacyEntity>>

    @Query("SELECT * FROM farmacy_table WHERE name = :nombre")
    fun getFarmacybyName (nombre: String): LiveData<FarmacyEntity>
}