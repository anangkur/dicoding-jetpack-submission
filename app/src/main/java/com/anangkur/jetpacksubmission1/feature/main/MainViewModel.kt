package com.anangkur.jetpacksubmission1.feature.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.data.model.Response
import com.anangkur.jetpacksubmission1.data.model.Result
import com.google.gson.Gson

class MainViewModel(application: Application, repository: Repository): AndroidViewModel(application){

    fun createDataTvPopular(jsonTVPopular: String): List<Result>{
        val response = Gson().fromJson(jsonTVPopular, Response::class.java)
        return response.results
    }

    fun createDataMoviePopular(jsonMoviePopular: String): List<Result>{
        val response = Gson().fromJson(jsonMoviePopular, Response::class.java)
        return response.results
    }
}