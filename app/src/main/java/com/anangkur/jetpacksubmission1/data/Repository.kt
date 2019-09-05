package com.anangkur.jetpacksubmission1.data

import com.anangkur.jetpacksubmission1.data.remote.RemoteRepository

class Repository(val remoteRepository: RemoteRepository){

    companion object{
        @Volatile private var INSTANCE: Repository? = null
        fun getInstance(remoteRepository: RemoteRepository) = INSTANCE ?: synchronized(Repository::class.java){
            INSTANCE ?: Repository(remoteRepository).also { INSTANCE = it }
        }
    }
}