package com.anangkur.jetpacksubmission1.data.remote

class RemoteRepository {



    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE ?: RemoteRepository()
    }
}