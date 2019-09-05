package com.anangkur.jetpacksubmission1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anangkur.jetpacksubmission1.data.model.Response
import com.anangkur.jetpacksubmission1.data.remote.RemoteRepository

class Repository(val remoteRepository: RemoteRepository): DataSource{

    override fun getData(page: Int, urlType: String, urlFilter: String, callback: DataSource.ProgressDialogCallback): LiveData<Response> {
        callback.onShowProgressDialog()
        val response = MutableLiveData<Response>()
        remoteRepository.getData(page, urlType, urlFilter, object: RemoteRepository.LoadMovieCallback{
            override fun onDataReceived(data: Response) {
                response.value = data
                callback.onHideProgressDialog()
            }
            override fun onDataNotAvailable() {
                callback.onHideProgressDialog()
            }
        })
        return response
    }

    companion object{
        @Volatile private var INSTANCE: Repository? = null
        fun getInstance(remoteRepository: RemoteRepository) = INSTANCE ?: synchronized(Repository::class.java){
            INSTANCE ?: Repository(remoteRepository).also { INSTANCE = it }
        }
    }
}