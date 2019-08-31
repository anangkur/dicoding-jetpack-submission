package com.anangkur.jetpacksubmission1.feature.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.anangkur.jetpacksubmission1.data.model.Result

class DetailViewModel(application: Application): AndroidViewModel(application){

    var result: Result? = null
}