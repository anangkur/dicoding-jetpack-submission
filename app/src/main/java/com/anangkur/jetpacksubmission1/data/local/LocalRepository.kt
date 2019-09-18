package com.anangkur.jetpacksubmission1.data.local

import com.anangkur.jetpacksubmission1.data.DataCallback
import com.anangkur.jetpacksubmission1.data.local.room.ResultDao
import com.anangkur.jetpacksubmission1.data.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocalRepository(private val resultDao: ResultDao){

    fun getAllResult(type: Int, callback: GetAllResultCallback){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = resultDao.getAllResult(type)
                withContext(Dispatchers.Main){
                    if (data.isNotEmpty()){
                        callback.onDataReceived(data)
                    }else{
                        callback.onDataNotAvailable()
                    }
                }
            }catch (e: Exception){
                callback.onDataNotAvailable()
            }
        }
    }

    fun bulkInsert(data: Result){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                resultDao.bulkInsert(data)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun deleteData(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                resultDao.deleteById(id)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun getResultById(id: Int, callback: GetResultByIdCallback){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = resultDao.getResultById(id)
                withContext(Dispatchers.Main){
                    callback.onDataReceived(data)
                }
            }catch (e: Exception){
                callback.onDataNotAvailable()
            }
        }
    }

    interface GetAllResultCallback: DataCallback<List<Result>>
    interface GetResultByIdCallback: DataCallback<Result>

    companion object{
        private var INSTANCE: LocalRepository? = null
        fun getInstance(resultDao: ResultDao) = INSTANCE ?: LocalRepository(resultDao)
    }
}