package com.kharol.internetspeed.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.kharol.internetspeed.database.ConsumptionDAO
import com.kharol.internetspeed.database.DailyConsumption
import com.kharol.internetspeed.util.DateUtils

class ConsumptionRepository(val consumptionDAO: ConsumptionDAO){

    val allConsumptions : LiveData<List<DailyConsumption>> = consumptionDAO.getAll()

    val getDayUsage : LiveData<DailyConsumption> = consumptionDAO.getDayUsage(DateUtils.getDayID())

    @WorkerThread
    suspend fun insert(dailyConsumption : DailyConsumption){
        consumptionDAO.insert(dailyConsumption)
    }

    @WorkerThread
    suspend fun updateWifiUsage(dayID : String, wifi : Long){
        consumptionDAO.updateWifiUsage(dayID, wifi)
    }

    @WorkerThread
    suspend fun updateMobileUsage(dayID : String, mobile : Long){
        consumptionDAO.updateMobileUsage(dayID, mobile)
    }

    @WorkerThread
    fun getDayUsageInBackgroundThread(dayID: String) : DailyConsumption{
        return consumptionDAO.getDayUsageInBackgroundThread(dayID)
    }

}