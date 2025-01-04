package com.easy.lokalsolution.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.lokalsolution.Class.NewsClass
import com.easy.lokalsolution.Repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel(){
    private val _allpost = MutableLiveData<List<NewsClass>>()
    val allpost: LiveData<List<NewsClass>> get() = _allpost

    fun loadAllPost() {
        viewModelScope.launch {
            try {
                val userList1 = repository.fetchAllPost()
                Log.d("UserViewModel", "Users loaded: $userList1")
                _allpost.postValue(userList1)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error loading users", e)
                _allpost.postValue(emptyList())
            }
        }
    }

      private val _onlynews = MutableLiveData<List<NewsClass>>()
    val onlynews: LiveData<List<NewsClass>> get() = _onlynews

    fun loadOnlyNews() {
        viewModelScope.launch {
            try {
                val userList = repository.fetchOnlyNews()
                Log.d("UserViewModel", "Users loaded: $userList")
                _onlynews.postValue(userList)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error loading users", e)
                _onlynews.postValue(emptyList())
            }
        }
    }

     private val _onlyAdvertisement = MutableLiveData<List<NewsClass>>()
    val onlyAdvertisement: LiveData<List<NewsClass>> get() = _onlyAdvertisement

    fun loadOnlyAdvertisement() {
        viewModelScope.launch {
            try {
                val userList = repository.fetchOnlyAdvertisement()
                Log.d("UserViewModel", "Users loaded: $userList")
                _onlyAdvertisement.postValue(userList)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error loading users", e)
                _onlyAdvertisement.postValue(emptyList())
            }
        }
    }

       private val _onlyStory = MutableLiveData<List<NewsClass>>()
    val onlyStory: LiveData<List<NewsClass>> get() = _onlyStory

    fun loadOnlyStory() {
        viewModelScope.launch {
            try {
                val userList = repository.fetchOnlyStory()
                Log.d("UserViewModel", "Users loaded: $userList")
                _onlyStory.postValue(userList)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error loading users", e)
                _onlyStory.postValue(emptyList())
            }
        }
    }
      private val _onlyPoetry = MutableLiveData<List<NewsClass>>()
    val onlyPoetry: LiveData<List<NewsClass>> get() = _onlyPoetry

    fun loadOnlyPoetry() {
        viewModelScope.launch {
            try {
                val userList = repository.fetchOnlyPoetry()
                Log.d("UserViewModel", "Users loaded: $userList")
                _onlyPoetry.postValue(userList)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error loading users", e)
                _onlyPoetry.postValue(emptyList())
            }
        }
    }

      private val _onlyAnnouncement = MutableLiveData<List<NewsClass>>()
    val onlyAnnouncement: LiveData<List<NewsClass>> get() = _onlyAnnouncement

    fun loadOnlyAnnouncement() {
        viewModelScope.launch {
            try {
                val userList = repository.fetchOnlyAnnouncement()
                Log.d("UserViewModel", "Users loaded: $userList")
                _onlyAnnouncement.postValue(userList)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error loading users", e)
                _onlyAnnouncement.postValue(emptyList())
            }
        }
    }


      private val _onlyPuzzle = MutableLiveData<List<NewsClass>>()
    val onlyPuzzle: LiveData<List<NewsClass>> get() = _onlyPuzzle

    fun loadOnlyPuzzle() {
        viewModelScope.launch {
            try {
                val userList = repository.fetchOnlyPuzzle()
                Log.d("UserViewModel", "Users loaded: $userList")
                _onlyPuzzle.postValue(userList)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error loading users", e)
                _onlyPuzzle.postValue(emptyList())
            }
        }
    }


      private val _onlyQuestion = MutableLiveData<List<NewsClass>>()
    val onlyQuestion: LiveData<List<NewsClass>> get() = _onlyQuestion

    fun loadOnlyQuestion() {
        viewModelScope.launch {
            try {
                val userList = repository.fetchOnlyQuestion()
                Log.d("UserViewModel", "Users loaded: $userList")
                _onlyQuestion.postValue(userList)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error loading users", e)
                _onlyQuestion.postValue(emptyList())
            }
        }
    }


      private val _onlyOther = MutableLiveData<List<NewsClass>>()
    val onlyOther: LiveData<List<NewsClass>> get() = _onlyOther

    fun loadOnlyOther() {
        viewModelScope.launch {
            try {
                val userList = repository.fetchOnlyOther()
                Log.d("UserViewModel", "Users loaded: $userList")
                _onlyOther.postValue(userList)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error loading users", e)
                _onlyOther.postValue(emptyList())
            }
        }
    }



}