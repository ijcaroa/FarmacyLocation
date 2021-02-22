package com.example.farmacylocation.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "farmacy_table")
 data class FarmacyEntity (
        @PrimaryKey
                   @NotNull
                   val id : String,
        val region : String,
        val name : String,
        val commune : String,
        val address : String,
        val tel : String,
        val lat : String,
        val longi : String,
        var fav : Boolean = false )



