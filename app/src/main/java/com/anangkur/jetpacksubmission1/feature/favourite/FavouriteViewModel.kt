package com.anangkur.jetpacksubmission1.feature.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.utils.Const

class FavouriteViewModel(private val repository: Repository): ViewModel(){
    fun getAllDataMovie(): LiveData<List<Result>> = repository.getAllResult(Const.TYPE_MOVIE)
    fun getAllDataTv(): LiveData<List<Result>> = repository.getAllResult(Const.TYPE_TV)
}