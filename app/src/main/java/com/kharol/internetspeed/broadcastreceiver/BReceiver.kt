package com.kharol.internetspeed.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kharol.internetspeed.services.TrafficStatusService

class BReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val intentService = Intent(context,TrafficStatusService::class.java)
        context!!.startService(intentService)
    }

}