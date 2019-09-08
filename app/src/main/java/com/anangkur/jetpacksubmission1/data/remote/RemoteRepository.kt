package com.anangkur.jetpacksubmission1.data.remote

import com.anangkur.jetpacksubmission1.BuildConfig.apiKey
import com.anangkur.jetpacksubmission1.data.model.Response
import com.anangkur.jetpacksubmission1.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class RemoteRepository {

    fun getData(page: Int, urlType: String, urlFilter: String, callback: LoadMovieCallback){
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiService.getApiService.getData(urlType, urlFilter, apiKey, page)
                withContext(Dispatchers.Main){
                    if (response.results.isNotEmpty()){
                        callback.onDataReceived(response)
                        EspressoIdlingResource.decrement()
                    }else{
                        callback.onDataNotAvailable()
                    }
                }
            }catch (e: Exception){
                callback.onDataNotAvailable()
            }
        }
    }

    interface LoadMovieCallback: LoadDataCallback<Response>

    interface LoadDataCallback<T>{
        fun onDataReceived(data: T)
        fun onDataNotAvailable()
    }

    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE ?: RemoteRepository()
    }
}