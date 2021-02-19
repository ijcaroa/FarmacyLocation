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

    @Query("SELECT * FROM farmacy_table ORDER BY region ASC ")
    fun getAllFarmacyByRegion(): LiveData<List<FarmacyEntity>>

    @Query("SELECT * FROM farmacy_table WHERE fav = 1")
    fun getAllFavLocal(): LiveData<List<FarmacyEntity>>



}