package com.anangkur.jetpacksubmission1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.anangkur.jetpacksubmission1.data.local.LocalRepository
import com.anangkur.jetpacksubmission1.data.model.Response
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.data.remote.RemoteRepository
import com.anangkur.jetpacksubmission1.utils.Const

class Repository(private val remoteRepository: RemoteRepository, private val localRepository: LocalRepository): DataSource{

    override fun getAllResultPaged(type: Int): LiveData<PagedList<Result>> = LivePagedListBuilder(localRepository.getAllResultPaged(type), 10).build()

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

    companion object{
        @Volatile private var INSTANCE: Repository? = null
        fun getInstance(remoteRepository: RemoteRepository, localRepository: LocalRepository) = INSTANCE ?: synchronized(Repository::class.java){
            INSTANCE ?: Repository(remoteRepository, localRepository).also { INSTANCE = it }
        }
    }
}