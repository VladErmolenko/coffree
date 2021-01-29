package com.example.roomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.CafeDataBase
import com.example.roomapp.model.Realization
import com.example.roomapp.repository.RealizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RealizationViewModel(application: Application): AndroidViewModel(application) {

    val readAllRealization: LiveData<List<Realization>>
    private val repository: RealizationRepository

    init {
        val cafeDao = CafeDataBase.getDatabase(application).cafeDao()
        repository = RealizationRepository(cafeDao)
        readAllRealization = repository.readAllRealization
    }

    fun addRealization(realization: Realization){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRealization(realization)
        }
    }
    fun updateRealization(realization: Realization){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRealization(realization)
        }
    }
    fun deleteRealization(realization: Realization){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRealization(realization)
        }
    }

    fun deleteAllRealization(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllRealization()
        }
    }

}
