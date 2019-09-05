package com.anangkur.jetpacksubmission1.feature.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anangkur.jetpacksubmission1.BuildConfig
import com.anangkur.jetpacksubmission1.data.DataSource
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.data.model.Response
import com.anangkur.jetpacksubmission1.data.model.Result
import com.google.gson.Gson

class MainViewModel(application: Application, val repository: Repository): AndroidViewModel(application){

    private val showProgressTv = MutableLiveData<Boolean>()
    private val showProgressMovie = MutableLiveData<Boolean>()

    fun getShowProgressTv(): LiveData<Boolean> = showProgressTv
    fun getShowProgressMovie(): LiveData<Boolean> = showProgressMovie

    fun getMoviePopular(page: Int): LiveData<Response> = repository.getData(page, BuildConfig.movieUrl, BuildConfig.popularUrl, object: DataSource.ProgressDialogCallback{
        override fun onShowProgressDialog() {
            showProgressMovie.value = true
        }
        override fun onHideProgressDialog() {
            showProgressMovie.value = false
        }
    })

    fun getTvPopular(page: Int): LiveData<Response> = repository.getData(page, BuildConfig.tvUrl, BuildConfig.popularUrl, object: DataSource.ProgressDialogCallback{
        override fun onShowProgressDialog() {
            showProgressTv.value = true
        }
        override fun onHideProgressDialog() {
            showProgressTv.value = false
        }
    })
}