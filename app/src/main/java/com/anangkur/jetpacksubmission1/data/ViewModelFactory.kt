package com.anangkur.jetpacksubmission1.data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anangkur.jetpacksubmission1.feature.detail.DetailViewModel
import com.anangkur.jetpacksubmission1.feature.main.MainViewModel

class ViewModelFactory(private val application: Application, private val repository: Repository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) -> MainViewModel(application, repository)
                isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(application, repository)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object{
        @Volatile private var INSTANCE: ViewModelFactory? = null
        fun getInstance(application: Application) = INSTANCE ?: synchronized(ViewModelFactory::class.java){
            INSTANCE ?: ViewModelFactory(application, Injection.provideRepository()).also { INSTANCE = it }
        }
    }
}