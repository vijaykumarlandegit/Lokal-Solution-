package com.easy.lokalsolution.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.lokalsolution.Class.QueryClass
import com.easy.lokalsolution.Repository.QueryRepository
import kotlinx.coroutines.launch

class QueryViewModel(private val repository:QueryRepository):ViewModel() {
    private val _allquery=MutableLiveData<List<QueryClass>>()
    val allquery:LiveData<List<QueryClass>> get() = _allquery

     fun loadAllQuery(){
        viewModelScope.launch {
            try {
                 _allquery.postValue(repository.fetchAllQuery())

            }catch (e:Exception){
                Log.e("UserViewModel", "Error loading users", e)
                _allquery.postValue(emptyList())
            }
        }
    } private val _allRentOutQuery=MutableLiveData<List<QueryClass>>()
    val allRentOutQuery:LiveData<List<QueryClass>> get() = _allRentOutQuery

     fun loadAllRentOutQuery(){
        viewModelScope.launch {
            try {
                 _allRentOutQuery.postValue(repository.fetchRentOutQuery())

            }catch (e:Exception){
                Log.e("UserViewModel", "Error loading users", e)
                _allRentOutQuery.postValue(emptyList())
            }
        }
    }private val _allNeedByRentQuery=MutableLiveData<List<QueryClass>>()
    val allNeedByRentQuery:LiveData<List<QueryClass>> get() = _allNeedByRentQuery

     fun loadAllNeedByRentQuery(){
        viewModelScope.launch {
            try {
                 _allNeedByRentQuery.postValue(repository.fetchNeedByRentQuery())

            }catch (e:Exception){
                Log.e("UserViewModel", "Error loading users", e)
                _allNeedByRentQuery.postValue(emptyList())
            }
        }
    }private val _allBuyQuery=MutableLiveData<List<QueryClass>>()
    val allBuyQuery:LiveData<List<QueryClass>> get() = _allBuyQuery

     fun loadAllBuyQuery(){
        viewModelScope.launch {
            try {
                 _allBuyQuery.postValue(repository.fetchBuyQuery())

            }catch (e:Exception){
                Log.e("UserViewModel", "Error loading users", e)
                _allBuyQuery.postValue(emptyList())
            }
        }
    }private val _allSellQuery=MutableLiveData<List<QueryClass>>()
    val allSellQuery:LiveData<List<QueryClass>> get() = _allSellQuery

     fun loadAllSellQuery(){
        viewModelScope.launch {
            try {
                 _allSellQuery.postValue(repository.fetchSellQuery())

            }catch (e:Exception){
                Log.e("UserViewModel", "Error loading users", e)
                _allSellQuery.postValue(emptyList())
            }
        }
    }private val _allJobQuery=MutableLiveData<List<QueryClass>>()
    val allJobQuery:LiveData<List<QueryClass>> get() = _allJobQuery

     fun loadAllJobQuery(){
        viewModelScope.launch {
            try {
                 _allJobQuery.postValue(repository.fetchJobQuery())

            }catch (e:Exception){
                Log.e("UserViewModel", "Error loading users", e)
                _allJobQuery.postValue(emptyList())
            }
        }
    }private val _allNeedJobQuery=MutableLiveData<List<QueryClass>>()
    val allNeedJobQuery:LiveData<List<QueryClass>> get() = _allNeedJobQuery

     fun loadAllNeedJobQuery(){
        viewModelScope.launch {
            try {
                 _allNeedJobQuery.postValue(repository.fetchNeedJobQuery())

            }catch (e:Exception){
                Log.e("UserViewModel", "Error loading users", e)
                _allNeedJobQuery.postValue(emptyList())
            }
        }
    }private val _allNeedWorkerQuery=MutableLiveData<List<QueryClass>>()
    val allNeedWorkerQuery:LiveData<List<QueryClass>> get() = _allNeedWorkerQuery

     fun loadAllNeedWorkerQuery(){
        viewModelScope.launch {
            try {
                 _allNeedWorkerQuery.postValue(repository.fetchNeedWorkerQuery())

            }catch (e:Exception){
                Log.e("UserViewModel", "Error loading users", e)
                _allNeedWorkerQuery.postValue(emptyList())
            }
        }
    }private val _allOtherQuery=MutableLiveData<List<QueryClass>>()
    val allOtherQuery:LiveData<List<QueryClass>> get() = _allOtherQuery

     fun loadAllOtherQuery(){
        viewModelScope.launch {
            try {
                 _allOtherQuery.postValue(repository.fetchOtherQuery())

            }catch (e:Exception){
                Log.e("UserViewModel", "Error loading users", e)
                _allOtherQuery.postValue(emptyList())
            }
        }
    }
}