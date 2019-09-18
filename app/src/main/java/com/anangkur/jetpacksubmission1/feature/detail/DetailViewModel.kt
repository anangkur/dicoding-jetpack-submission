package com.anangkur.jetpacksubmission1.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.data.model.Result

class DetailViewModel(private val repository: Repository): ViewModel(){

    var result: Result? = null
    var type: Int = 0

    fun getResultById(id: Int): LiveData<Result> = repository.getResultById(id)
    fun deleteResultById(id: Int){ repository.deleteData(id) }
    fun bulkInsert(data: Result){ repository.bulkInsert(data) }
}