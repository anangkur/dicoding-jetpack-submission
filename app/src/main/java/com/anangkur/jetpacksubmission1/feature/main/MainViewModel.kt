package com.anangkur.jetpacksubmission1.feature.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anangkur.jetpacksubmission1.data.model.Response
import com.anangkur.jetpacksubmission1.data.model.Result
import com.google.gson.Gson

class MainViewModel(application: Application): AndroidViewModel(application){

    private var listTvPopular: MutableLiveData<List<Result>>? = null
    private var listMoviePopular: MutableLiveData<List<Result>>? = null

    fun getListTvPopular(jsonTVPopular: String): LiveData<List<Result>>{
        if (listTvPopular == null){
            listTvPopular = MutableLiveData()
            createDataTvPopular(jsonTVPopular)
        }

        return listTvPopular!!
    }

    fun getListMoviePopular(jsonMoviePopular: String): LiveData<List<Result>>{
        if (listMoviePopular == null){
            listMoviePopular = MutableLiveData()
            createDataMoviePopular(jsonMoviePopular)
        }

        return listMoviePopular!!
    }

    private fun createDataTvPopular(jsonTVPopular: String){
        val response = Gson().fromJson(jsonTVPopular, Response::class.java)
        listTvPopular?.value = response.results
    }

    private fun createDataMoviePopular(jsonMoviePopular: String){
        val response = Gson().fromJson(jsonMoviePopular, Response::class.java)
        listMoviePopular?.value = response.results
    }
}