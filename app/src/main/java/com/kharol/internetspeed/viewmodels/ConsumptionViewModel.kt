package com.kharol.internetspeed.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kharol.internetspeed.database.ConsumptionDatabase
import com.kharol.internetspeed.database.DailyConsumption
import com.kharol.internetspeed.repository.ConsumptionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConsumptionViewModel(application: Application) : AndroidViewModel(application){

    private val repository : ConsumptionRepository
    val allConsumptions : LiveData<List<DailyConsumption>>

    init {
        val consumptionDao = ConsumptionDatabase.getDatabase(application).consumptionDAO()
        repository = ConsumptionRepository(consumptionDao)
        allConsumptions = repository.allConsumptions
    }

    fun getDayUsage() : LiveData<DailyConsumption>{
        return repository.getDayUsage
    }

    fun insert(dailyConsumption: DailyConsumption) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(dailyConsumption)
    }

    fun updateWifiUsage(dayID : String, wifi : Long) = viewModelScope.launch(Dispatchers.IO){
        repository.updateWifiUsage(dayID,wifi)
    }

    fun updateMobileUsage(dayID : String, mobile : Long) = viewModelScope.launch(Dispatchers.IO){
        repository.updateMobileUsage(dayID,mobile)
    }

    fun getDayUsageInBackgroundThread(dayID: String) : DailyConsumption{
        return repository.getDayUsageInBackgroundThread(dayID)
    }

}