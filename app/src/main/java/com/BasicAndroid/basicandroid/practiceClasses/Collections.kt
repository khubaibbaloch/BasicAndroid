package com.BasicAndroid.basicandroid.practiceClasses

import android.util.Log

// Data class representing a person with a name and age
data class Vote(val name: String, val age: Int)

class Collections {

    // 🔹 map - Transforms each element of the list and returns a new list
    fun mapPractice() {
        val names = listOf(
            Vote(name = "khubaib", age = 21),
            Vote(name = "parvez", age = 25),
            Vote(name = "zain", age = 12)
        )
        Log.d("map", "mapPractice: $names")

        // If age is greater than 18, keep the object; otherwise, replace with an empty string
        val condition = names.map { if (it.age > 18) it else "" }
        Log.d("map", "mapPractice: $condition")

        // Convert all names to uppercase
        val uppercase = names.map { it.name.uppercase() }
        Log.d("map", "mapPractice: $uppercase")

        // Convert all names to lowercase
        val lowercase = names.map { it.name.lowercase() }
        Log.d("map", "mapPractice: $lowercase")
    }

    // 🔹 filter - Filters the list based on a condition
    fun filterPractice() {
        val names = listOf(
            Vote(name = "khubaib", age = 21),
            Vote(name = "parvez", age = 25),
            Vote(name = "zain", age = 12)
        )
        Log.d("filter", "Original List: $names")

        // Find all objects where the name is "zain"
        val name = names.filter { it.name == "zain" }
        Log.d("filter", "Filtered by name (zain): $name")

        // Find objects based on index (index 2 in this case)
        val nameIndex = names.filterIndexed { index, _ ->
            index == 2
        }
        Log.d("filter", "Filtered by index (2): $nameIndex")

        // filterTo - Filters elements and adds them to a new list
        val newNames = mutableListOf<Vote>()
        val nameTo = names.filterTo(newNames) { it.name == "Zain" }
        Log.d("filter", "Filtered elements added to newNames: $nameTo")
    }

    // 🔹 reduce - Combines all elements of the list into a single value
    fun reducePractice() {
        val age = listOf(1, 2, 3, 4)

        // Accumulates values step by step
        val check = age.reduce { acc, number ->
            Log.d("reduce", "reducePractice: acc:$acc, number:$number")
            acc + number // Sum up all numbers
        }
        Log.d("reduce", "Final reduced value: $check") // Output: 10 (1+2+3+4)
    }

    // 🔹 zip - Combines two lists element by element into pairs
    fun zipPractice() {
        val name = listOf("khubaib", "parvez", "zain")
        val skill = listOf("Dev", "Dev", "Gando")

        // Basic zip - Pairs each element from both lists
        val merge = name.zip(skill)
        Log.d("zip", "zipPractice: $merge") // Output: [(khubaib, Dev), (parvez, Dev), (zain, Gando)]

        // zip with transformation - Custom message using Name and Skill
        val addInfo = name.zip(skill) { Name, Skill ->
            Log.d("zip", "$Name is good at $Skill")
        }
    }

    // 🔹 groupBy - Groups elements by a key (age in this case)
    fun groupByPractice() {
        val students = listOf(
            Vote("Alice", 20),
            Vote("Bob", 21),
            Vote("Charlie", 20),
            Vote("David", 22),
            Vote("Eve", 21)
        )

        // Groups students by age
        val ageGrouped = students.groupBy { it.age }
        Log.d("groupBy", "groupByPractice: $ageGrouped")
        // Output: {20=[Alice, Charlie], 21=[Bob, Eve], 22=[David]}
    }
}
