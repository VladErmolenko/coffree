package com.example.roomapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.model.Assortment
import com.example.roomapp.model.Cafe
import com.example.roomapp.model.Realization
import com.example.roomapp.model.User

@Database(entities = [User::class, Cafe::class,Assortment::class,Realization::class], version = 2, exportSchema = false)
abstract class CafeDataBase : RoomDatabase() {

    abstract fun cafeDao(): CafeDao

    companion object {
        @Volatile
        private var INSTANCE: CafeDataBase? = null

        fun getDatabase(context: Context): CafeDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CafeDataBase::class.java,
                    "cafe_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}