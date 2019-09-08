package com.anangkur.jetpacksubmission1.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anangkur.jetpacksubmission1.BuildConfig
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.data.model.Response

class MainViewModel(private val repository: Repository): ViewModel(){

    fun getMoviePopular(page: Int): LiveData<Response> = repository.getData(page, BuildConfig.movieUrl, BuildConfig.popularUrl)

    fun getTvPopular(page: Int): LiveData<Response> = repository.getData(page, BuildConfig.tvUrl, BuildConfig.popularUrl)
}