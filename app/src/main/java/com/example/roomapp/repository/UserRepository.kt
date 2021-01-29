package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.data.CafeDao
import com.example.roomapp.model.User

class UserRepository(private val cafeDao: CafeDao) {

    val readAllData: LiveData<List<User>> = cafeDao.readAllData()

    suspend fun addUser(user: User){
        cafeDao.addUser(user)

    }

    suspend fun updateUser(user: User){
        cafeDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        cafeDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        cafeDao.deleteAllUsers()
    }

}