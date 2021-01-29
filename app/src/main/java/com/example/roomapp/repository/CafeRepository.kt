package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.data.CafeDao
import com.example.roomapp.model.Cafe
import com.example.roomapp.model.User

class CafeRepository(private val cafeDao: CafeDao) {
    val readAllCafeData: LiveData<List<Cafe>> = cafeDao.readAllCafeData()

    suspend fun addCafe(cafe: Cafe){
        cafeDao.addCafe(cafe)
    }

    suspend fun updateCafe(cafe: Cafe){
        cafeDao.updateCafe(cafe)
    }

    suspend fun deleteCafe(cafe: Cafe){
        cafeDao.deleteCafe(cafe)
    }

    suspend fun deleteAllCafe(){
        cafeDao.deleteAllCafe()
    }
}