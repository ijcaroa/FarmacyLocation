package com.example.farmacylocation.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [FarmacyEntity::class],version = 1)
abstract class FarmacyDataBase:RoomDatabase() {
    abstract fun farmacyDao () : FarmacyDao

    companion object {
        @Volatile
        private var INSTANCE : FarmacyDataBase? = null

        fun getDataBase (context: Context): FarmacyDataBase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FarmacyDataBase::class.java,
                        "farmacydb").build()
                         INSTANCE  = instance
                         return instance
            }
        }
    }
}