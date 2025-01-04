package com.easy.lokalsolution.ViewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.lokalsolution.Model.User
import com.easy.lokalsolution.Repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    fun loadUsers() {
        viewModelScope.launch {
            try {
                val userList = repository.fetchUsers()
                Log.d("UserViewModel", "Users loaded: $userList")
                _users.postValue(userList)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error loading users", e)
                _users.postValue(emptyList())
            }
        }
    }
}



