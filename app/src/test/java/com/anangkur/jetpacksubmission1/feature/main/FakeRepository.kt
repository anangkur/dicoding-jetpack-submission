package com.anangkur.jetpacksubmission1.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anangkur.jetpacksubmission1.data.DataSource
import com.anangkur.jetpacksubmission1.data.model.Response
import com.anangkur.jetpacksubmission1.data.remote.RemoteRepository

class FakeRepository(private val remoteRepository: RemoteRepository): DataSource {

    override fun getData(page: Int, urlType: String, urlFilter: String): LiveData<Response> {
        val response = MutableLiveData<Response>()
        remoteRepository.getData(page, urlType, urlFilter, object: RemoteRepository.LoadMovieCallback{
            override fun onDataReceived(data: Response) {
                response.value = data
            }
            override fun onDataNotAvailable() {

            }
        })
        return response
    }
}