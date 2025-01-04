package com.easy.lokalsolution.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.easy.lokalsolution.Repository.QueryRepository
import com.easy.lokalsolution.ViewModel.QueryViewModel

class QueryViewModelFactory(private var repository: QueryRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(QueryViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return QueryViewModel(repository) as T
        }
        throw IllegalArgumentException ("Unknown viewModel class")

    }

}
