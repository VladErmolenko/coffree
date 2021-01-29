package com.example.roomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.CafeDataBase
import com.example.roomapp.model.Assortment
import com.example.roomapp.model.Cafe
import com.example.roomapp.repository.AssortmentRepository
import com.example.roomapp.repository.CafeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssortmentViewModel(application: Application): AndroidViewModel(application) {

    val readAllAssorment: LiveData<List<Assortment>>
    private val repository: AssortmentRepository

    init {
        val cafeDao = CafeDataBase.getDatabase(
            application
        ).cafeDao()
        repository = AssortmentRepository(cafeDao)
        readAllAssorment = repository.readAllAssortment
    }

    fun addAssortment(assortment: Assortment){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAssortment(assortment)
        }
    }
    fun updateAssortment(assortment: Assortment){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAssortment(assortment)
        }
    }
    fun deleteAssortment(assortment: Assortment){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAssortment(assortment)
        }
    }

    fun deleteAllAssortment(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllAssortment()
        }
    }

}