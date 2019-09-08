package com.anangkur.jetpacksubmission1.feature.detail

import androidx.lifecycle.ViewModel
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.data.model.Result

class DetailViewModel(repository: Repository): ViewModel(){

    var result: Result? = null
}