package com.anangkur.jetpacksubmission1.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anangkur.jetpacksubmission1.feature.detail.DetailViewModel
import com.anangkur.jetpacksubmission1.feature.favourite.FavouriteViewModel
import com.anangkur.jetpacksubmission1.feature.main.MainViewModel

class ViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) -> MainViewModel(repository)
                isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository)
                isAssignableFrom(FavouriteViewModel::class.java) -> FavouriteViewModel(repository)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object{
        @Volatile private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context) = INSTANCE ?: synchronized(ViewModelFactory::class.java){
            INSTANCE ?: ViewModelFactory(Injection.provideRepository(context)).also { INSTANCE = it }
        }
    }
}