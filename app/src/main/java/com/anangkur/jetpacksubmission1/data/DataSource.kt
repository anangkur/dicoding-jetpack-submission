package com.anangkur.jetpacksubmission1.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.anangkur.jetpacksubmission1.data.model.Response
import com.anangkur.jetpacksubmission1.data.model.Result

interface DataSource {
    fun getData(page: Int, urlType: String, urlFilter: String): LiveData<Response>

    fun bulkInsert(data: Result)
    fun deleteData(id: Int)
    fun getResultById(id: Int): LiveData<Result>

    fun getAllResultPaged(type: Int): androidx.paging.DataSource.Factory<Int, Result>
}