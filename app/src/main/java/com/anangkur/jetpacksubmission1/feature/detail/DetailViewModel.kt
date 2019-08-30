package com.anangkur.jetpacksubmission1.feature.detail

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.utils.Const

class DetailViewModel(application: Application): AndroidViewModel(application){

    var result: Result? = null
}