package com.anangkur.jetpacksubmission1.feature.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.utils.Const

class FavouriteViewModel(private val repository: Repository): ViewModel(){
    fun getAllDataMoviePaged(): LiveData<PagedList<Result>> = repository.getAllResultPaged(Const.TYPE_MOVIE)
    fun getAllDataTvPaged(): LiveData<PagedList<Result>> = repository.getAllResultPaged(Const.TYPE_TV)
}