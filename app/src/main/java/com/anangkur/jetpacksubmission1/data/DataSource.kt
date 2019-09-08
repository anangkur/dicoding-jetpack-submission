package com.anangkur.jetpacksubmission1.data

import androidx.lifecycle.LiveData
import com.anangkur.jetpacksubmission1.data.model.Response

interface DataSource {
    fun getData(page: Int, urlType: String, urlFilter: String): LiveData<Response>
}