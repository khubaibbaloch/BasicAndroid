package com.BasicAndroid.basicandroid.practiceClasses

import android.util.Log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class Coroutines {
    suspend fun fetchData(): String {
        delay(5000L) // Simulates a long-running operation
        return "Data fetched"
    }

    fun runBlockingSimple() {
        runBlocking {
            Log.d("Coroutines", "runBlockingSimple: Start")
            delay(5000)
            Log.d("Coroutines", "runBlockingSimple: Stop")
        }
    }

    fun runBlockingLaunchSimple() {
        runBlocking {
            launch {
                Log.d("Coroutines", "runBlockingLaunchSimple: Start1")
                delay(5000)
                Log.d("Coroutines", "runBlockingLaunchSimple: Stop1")
            }
            launch {
                Log.d("Coroutines", "runBlockingLaunchSimple: Start2")
                delay(1000)
                Log.d("Coroutines", "runBlockingLaunchSimple: Stop2")
            }
            Log.d("Coroutines", "runBlockingLaunchSimple: finshed")

        }
    }

    fun runBlockingLaunchJobSimple() {
        runBlocking {
            val job1: Job = launch {
                Log.d("Coroutines", "runBlockingLaunchJobSimple: Start1")
                delay(5000)
                Log.d("Coroutines", "runBlockingLaunchJobSimple: Stop1")
            }
            val job2: Job = launch {
                Log.d("Coroutines", "runBlockingLaunchJobSimple: Start2")
                delay(1000)
                Log.d("Coroutines", "runBlockingLaunchJobSimple: Stop2")
            }
            Log.d("Coroutines", "runBlockingLaunchJobSimple: finshed")

            job1.join()
            job2.join()
        }
    }

    fun runBlockingAsyncSimple() {
        runBlocking {
            val deferred1: Deferred<String> = async {
                Log.d("Coroutines", "runBlockingAsyncSimple: Start1")
                delay(5000)
                Log.d("Coroutines", "runBlockingAsyncSimple: Stop1")
                "finshed1"
            }
            val deferred2: Deferred<String> = async {
                Log.d("Coroutines", "runBlockingAsyncSimple: Start2")
                delay(1000)
                Log.d("Coroutines", "runBlockingAsyncSimple: Stop2")
                "finshed2"
            }
            Log.d("Coroutines", "runBlockingAsyncSimple: ${deferred1.await()}")
            Log.d("Coroutines", "runBlockingAsyncSimple: ${deferred2.await()}")
            Log.d("Coroutines", "runBlockingAsyncSimple: finshed")
        }
    }

    fun coroutineScopeSimple() {
        runBlocking {
            coroutineScope {
                launch {
                    Log.d("Coroutines", "coroutineScopeSimple: Start1")
                    delay(5000)
                    Log.d("Coroutines", "coroutineScopeSimple: Stop1")
                }
                launch {
                    Log.d("Coroutines", "coroutineScopeSimple: Start2")
                    delay(1000)
                    Log.d("Coroutines", "coroutineScopeSimple: Stop2")
                }
                Log.d("Coroutines", "coroutineScopeSimple: runBlocking finshed")

            }
            Log.d("Coroutines", "coroutineScopeSimple: coroutineScope finshed")

        }

    }

    fun withContextSimple() {
        runBlocking{
            withContext(Dispatchers.IO){
                delay(1000L)
                "Data fetched"
            }
        }

    }
}