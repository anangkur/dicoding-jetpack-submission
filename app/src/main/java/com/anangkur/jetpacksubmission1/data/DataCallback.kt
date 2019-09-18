package com.anangkur.jetpacksubmission1.data

interface DataCallback<T>{
    fun onDataReceived(data: T)
    fun onDataNotAvailable()
}