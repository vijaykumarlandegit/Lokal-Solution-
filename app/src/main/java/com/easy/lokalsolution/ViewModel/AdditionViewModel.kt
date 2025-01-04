package com.easy.lokalsolution.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdditionViewModel : ViewModel() {
     private val _result = MutableLiveData<Int>()
    val result: LiveData<Int> get() = _result

    fun addNumbers(number1: Int, number2: Int) {
        _result.value = number1 + number2//setvalue
    }
}
