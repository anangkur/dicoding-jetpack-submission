package com.anangkur.jetpacksubmission1.feature.detail

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.utils.Const

class DetailViewModel(application: Application): AndroidViewModel(application){

    private var result: MutableLiveData<Result>? = null

    fun getResult(intent: Intent): LiveData<Result>{
        if (result == null){
            result = MutableLiveData()
            getResultIntent(intent)
        }

        return result!!
    }

    private fun getResultIntent(intent: Intent){
        if (intent.hasExtra(Const.EXTRA_DETAIL)){
            result?.value = intent.getParcelableExtra(Const.EXTRA_DETAIL)
        }
    }
}