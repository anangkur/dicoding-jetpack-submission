package com.anangkur.jetpacksubmission1.data

import com.anangkur.jetpacksubmission1.data.remote.RemoteRepository

object Injection {
    fun provideRepository() = Repository(RemoteRepository.getInstance())
}