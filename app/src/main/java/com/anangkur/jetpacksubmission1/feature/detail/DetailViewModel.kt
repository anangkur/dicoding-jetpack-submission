package com.anangkur.jetpacksubmission1.feature.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.data.model.Result

class DetailViewModel(application: Application, repository: Repository): AndroidViewModel(application){

    var result: Result? = null
}