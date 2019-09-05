package com.anangkur.jetpacksubmission1.feature.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.anangkur.jetpacksubmission1.BuildConfig
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.data.model.Response
import com.anangkur.jetpacksubmission1.data.model.Result
import com.google.gson.Gson

class MainViewModel(application: Application, val repository: Repository): AndroidViewModel(application){

    fun getMoviePopular(page: Int): LiveData<Response> = repository.getData(page, BuildConfig.movieUrl, BuildConfig.popularUrl)

    fun getTvPopular(page: Int): LiveData<Response> = repository.getData(page, BuildConfig.tvUrl, BuildConfig.popularUrl)

    fun createDataTvPopular(jsonTVPopular: String): List<Result>{
        val response = Gson().fromJson(jsonTVPopular, Response::class.java)
        return response.results
    }

    fun createDataMoviePopular(jsonMoviePopular: String): List<Result>{
        val response = Gson().fromJson(jsonMoviePopular, Response::class.java)
        return response.results
    }
}