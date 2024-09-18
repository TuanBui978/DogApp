package com.example.dogapp.presentation.ui.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.dogapp.MyApplication
import com.example.dogapp.domain.models.Dog
import com.example.dogapp.domain.repository.DogRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(private val application: MyApplication,private val repository: DogRepository = application.dogRepository ): ViewModel() {
    private var _dogList = MutableLiveData<List<Dog>>()
    val dogList get() = _dogList
    private var apiJob: Job? = null

    init {
        apiJob = viewModelScope.launch {
            _dogList.postValue(repository.getAllDog())
        }
    }


    companion object {
        @Suppress("UNCHECKED_CAST")
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return HomeViewModel(application as MyApplication) as T
            }
        }
    }


}