package com.BasicAndroid.basicandroid.RetrofitPractice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts

    init {
        fetchContacts()
    }

    private fun fetchContacts() {

        viewModelScope.launch {
            try {
                val contactList = repository.getContacts()
                _contacts.value = contactList
                Log.d("contactList", "fetchContacts: ${repository.getContacts()}")
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            try {
                repository.addContact(contact)
                fetchContacts() // Refresh the list
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun updateContact(id: Int, contact: Contact) {
        viewModelScope.launch {
            try {
                repository.updateContact(id, contact)
                fetchContacts() // Refresh the list
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun deleteContact(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteContact(id)
                fetchContacts() // Refresh the list
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
