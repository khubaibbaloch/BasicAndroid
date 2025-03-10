package com.BasicAndroid.basicandroid.RetrofitPractice

import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getContacts() = apiService.getContacts()
    suspend fun addContact(contact: Contact) = apiService.addContact(contact)
    suspend fun updateContact(id: Int, contact: Contact) = apiService.updateContact(id, contact)
    suspend fun deleteContact(id: Int) = apiService.deleteContact(id)
}

