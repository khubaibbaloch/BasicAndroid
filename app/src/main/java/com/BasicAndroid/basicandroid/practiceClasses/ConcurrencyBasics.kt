package com.BasicAndroid.basicandroid.practiceClasses

import android.util.Log

class ConcurrencyBasics {

}
class myThread: Thread(){
    override fun run() {
        super.run()
        println()
        Log.d("thread","Thread running: ${currentThread().name}")
    }
}