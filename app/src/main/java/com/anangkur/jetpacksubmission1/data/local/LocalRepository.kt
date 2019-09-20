package com.anangkur.jetpacksubmission1.data.local

import androidx.paging.DataSource
import com.anangkur.jetpacksubmission1.data.DataCallback
import com.anangkur.jetpacksubmission1.data.local.room.ResultDao
import com.anangkur.jetpacksubmission1.data.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocalRepository(private val resultDao: ResultDao){

    fun getAllResultPaged(type: Int): DataSource.Factory<Int, Result> = resultDao.getAllResultPaged(type)

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

    interface GetResultByIdCallback: DataCallback<Result>

    companion object{
        private var INSTANCE: LocalRepository? = null
        fun getInstance(resultDao: ResultDao) = INSTANCE ?: LocalRepository(resultDao)
    }
}