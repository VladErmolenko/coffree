package com.example.roomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.CafeDataBase
import com.example.roomapp.model.Cafe
import com.example.roomapp.repository.CafeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CafeViewModel(application: Application): AndroidViewModel(application) {

    val readAllCafeData: LiveData<List<Cafe>>
    private val repository: CafeRepository

    init {
        val cafeDao = CafeDataBase.getDatabase(
            application
        ).cafeDao()
        repository = CafeRepository(cafeDao)
        readAllCafeData = repository.readAllCafeData
    }

    fun addCafe(cafe: Cafe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCafe(cafe)
        }
    }
    fun updateCafe(cafe: Cafe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCafe(cafe)
        }
    }
    fun deleteCafe(cafe: Cafe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCafe(cafe)
        }
    }

    fun deleteAllCafe(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCafe()
        }
    }

}
