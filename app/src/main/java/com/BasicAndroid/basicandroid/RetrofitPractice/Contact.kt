package com.BasicAndroid.basicandroid.RetrofitPractice

import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("id") val id: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("Email") val email: String,
    @SerializedName("Phone") val phone: String
)
