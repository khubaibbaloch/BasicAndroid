package com.BasicAndroid.basicandroid.RetrofitPractice

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("rest/contacts")
    suspend fun getContacts(): List<Contact>

    @POST("rest/contacts")
    suspend fun addContact(@Body contact: Contact): Contact

    @PUT("rest/contacts/{id}")
    suspend fun updateContact(@Path("id") id: Int, @Body contact: Contact): Contact

    @DELETE("rest/contacts/{id}")
    suspend fun deleteContact(@Path("id") id: Int)
}

