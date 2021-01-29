package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.data.CafeDao
import com.example.roomapp.model.Assortment
import com.example.roomapp.model.Cafe

class AssortmentRepository (private val cafeDao: CafeDao){
    val readAllAssortment: LiveData<List<Assortment>> = cafeDao.readAllAssortmentData()

    suspend fun addAssortment(assortment: Assortment){
        cafeDao.addAssortment(assortment)
    }

    suspend fun updateAssortment(assortment: Assortment){
        cafeDao.updateAssortment(assortment)
    }

    suspend fun deleteAssortment(assortment: Assortment){
        cafeDao.deleteAssortment(assortment)
    }

    suspend fun deleteAllAssortment(){
        cafeDao.deleteAllAssortment()
    }
}