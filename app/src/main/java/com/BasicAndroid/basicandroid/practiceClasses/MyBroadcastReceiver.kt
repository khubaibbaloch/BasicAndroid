package com.BasicAndroid.basicandroid.practiceClasses

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver: BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BATTERY_LOW){
            Toast.makeText(context,"BATTERY is low", Toast.LENGTH_SHORT).show()
        }
    }

}