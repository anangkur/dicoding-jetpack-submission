package com.anangkur.jetpacksubmission1.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anangkur.jetpacksubmission1.data.DataSource
import com.anangkur.jetpacksubmission1.data.local.LocalRepository
import com.anangkur.jetpacksubmission1.data.model.Response
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.data.remote.RemoteRepository

class FakeRepository(private val remoteRepository: RemoteRepository, private val localRepository: LocalRepository): DataSource {

    override fun bulkInsert(data: Result) {
        localRepository.bulkInsert(data)
    }

    override fun deleteData(id: Int) {
        localRepository.deleteData(id)
    }

    override fun getResultById(id: Int): LiveData<Result> {
        val response = MutableLiveData<Result>()
        localRepository.getResultById(id, object: LocalRepository.GetResultByIdCallback{
            override fun onDataReceived(data: Result) {
                response.value = data
            }
            override fun onDataNotAvailable() {
                response.postValue(null)
            }
        })
        return response
    }

    override fun getAllResultPaged(type: Int): androidx.paging.DataSource.Factory<Int, Result> = localRepository.getAllResultPaged(type)

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