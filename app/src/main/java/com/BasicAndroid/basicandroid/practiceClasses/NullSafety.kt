package com.BasicAndroid.basicandroid.practiceClasses

import android.util.Log

// Data class to represent a user with name and age properties
data class User(var name: String, var age: Int)

class NullSafety {

    // Safe Call (?.) - Safely access nullable variable
    fun safeCall() {
        val name: String? = null
        // ?. ensures that if name is null, it won't crash, and will return null instead
        Log.d("safeCall", "safeCall: ${name?.length}")
    }

    // Non-Null Assertion (!!) - Forces a nullable variable to be non-null (Risky)
    fun NonNull() {
        val name: String? = null
        // !! will throw NullPointerException if name is null (should be avoided)
        Log.d("safeCall", "safeCall: ${name!!.length}")
    }

    // let - Only executes the block if the variable is non-null
    fun letNonNull() {
        val name: String? = "khubaib"
        name?.let {
            // Executes only if name is NOT null
            Log.d("safeCall", "safeCall: ${name.length}")
        }
    }

    // run - Groups multiple operations and returns the last expression
    fun Run() {
        val fullName = run {
            val firstName = "khubaib"
            val lastName = "Aziz Khan"
            "$firstName $lastName" // Returns this value
        }
        Log.d("run", "Run: $fullName") // Output: khubaib Aziz Khan
    }

    // apply - Used for modifying object properties in a chain (Returns the object itself)
    fun Apply() {
        val user = User(name = "khubaib", age = 20).apply {
            age = 200 // Modifies the age property
        }
        Log.d("apply", "Apply: $user") // Output: User(name=khubaib, age=200)
    }

    // also - Used for performing side-effects (like logging) without modifying the object
    fun Also() {
        val user = User(name = "khubaib", age = 20).also {
            Log.d("also", "also: $it") // Logs the user object
        }
        Log.d("also", "also: $user") // Output will be the same user object
    }
}
