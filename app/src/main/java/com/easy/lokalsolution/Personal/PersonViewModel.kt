package com.easy.lokalsolution.Personal

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class PersonViewModel(application: Application) : AndroidViewModel(application) {

  /*  private val repository: PersonRepository
    val allPersons: LiveData<List<PersonEntity>>

    init {
        val personDao = PersonDatabase.getDatabase(application).personDao()
        repository = PersonRepository(personDao)
        allPersons = repository.allPersons
    }

    fun insert(person: PersonEntity) = viewModelScope.launch {
        repository.insert(person)
    }

    fun delete(person: PersonEntity) = viewModelScope.launch {
        repository.delete(person)
    }*/
}


