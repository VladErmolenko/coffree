package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.data.CafeDao
import com.example.roomapp.model.Realization
//More OOP plus patterns
class RealizationRepository (private val cafeDao: CafeDao){// create single interface for repository
        val readAllRealization: LiveData<List<Realization>> = cafeDao.readAllRealization()//Use generics

        suspend fun addRealization(realization: Realization){
            cafeDao.addRealization(realization)
        }

        suspend fun updateRealization(realization: Realization){
            cafeDao.updateRealization(realization)
        }

        suspend fun deleteRealization(realization: Realization){
            cafeDao.deleteRealization(realization)
        }

        suspend fun deleteAllRealization(){
            cafeDao.deleteAllRealization()
        }
    }