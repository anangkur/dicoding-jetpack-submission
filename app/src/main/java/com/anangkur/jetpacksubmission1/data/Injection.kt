package com.anangkur.jetpacksubmission1.data

import android.content.Context
import com.anangkur.jetpacksubmission1.data.local.LocalRepository
import com.anangkur.jetpacksubmission1.data.local.room.ResultDatabase
import com.anangkur.jetpacksubmission1.data.remote.RemoteRepository

object Injection {
    fun provideRepository(context: Context) = Repository.getInstance(
        RemoteRepository.getInstance(),
        LocalRepository.getInstance(ResultDatabase.getInstance(context).getDao())
    )
}