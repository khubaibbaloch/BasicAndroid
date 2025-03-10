package com.BasicAndroid.basicandroid.practiceClasses

import android.util.Log
import com.BasicAndroid.basicandroid.practiceClasses.LoginResult.Success

// 🔹 Data class: Represents an immutable object with auto-generated functions like copy(), toString(), equals(), hashCode().
data class Practice(val id: Int = 0, val name: String = "")

// 🔹 Sealed interface: Used for defining restricted class hierarchies.
sealed interface UiState

// 🔹 Sealed class: A sealed class restricts class hierarchy to only defined subclasses.
sealed class LoginResult : UiState {
    // Represents a successful login with an integer message code
    data class Success(val message: Int) : LoginResult()

    // Represents a failed login with an error message
    data class Failure(val message: String) : LoginResult()
}

// 🔹 Object declaration: Represents a singleton instance of UiState (used to indicate loading state)
object Loading : UiState

// 🔹 Sealed class for error handling with predefined error types
sealed class Error(val message: String) {
    // Subclass representing a network error with a predefined message
    data class NetworkError(val inform: String) : Error("Network error occurred")
}

class BasicClasses {

    // 🔹 Function demonstrating data class usage
    fun dataClass() {
        // Creating an instance of the Practice data class
        val user = Practice(1, "user")
        Log.d("dataclass", "Original Data: $user")

        // Creating a copy with a modified name
        val userdata = user.copy(name = "newName")
        Log.d("dataclass", "Modified Data: $userdata")
    }

    // 🔹 Function demonstrating sealed class usage with when statement
    fun sealedPractice(uiState: UiState) {
        when (uiState) {
            is Success -> {
                // Calling function to process a payment when login is successful
                processCreditCardPayment(uiState.message)
                Log.d("sealed", "Login Successful")
            }
            is LoginResult.Failure -> {
                Log.d("sealed", "Login Failed: ${uiState.message}")
            }
            Loading -> {
                Log.d("sealed", "Loading...")
            }
        }
    }
}

// 🔹 Function to simulate a payment processing
fun processCreditCardPayment(number: Int) {
    Log.d("sealed", "Processing Payment: Transaction ID $number")
}
