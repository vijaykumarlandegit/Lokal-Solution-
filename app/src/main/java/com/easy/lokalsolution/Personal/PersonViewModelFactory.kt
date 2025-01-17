package com.easy.lokalsolution.Personal

import androidx.lifecycle.ViewModelProvider

class PersonViewModelFactory(private val repository: PersonRepository) : ViewModelProvider.Factory {
   /* override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PersonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }*/
}