package com.anangkur.jetpacksubmission1.feature.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anangkur.jetpacksubmission1.BuildConfig
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.data.model.Response
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule



class MainViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    @Mock private lateinit var application: Application
    @Mock private lateinit var repository: Repository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(application, repository)
    }

    @Test
    fun getMoviePopular(){
        val fakeData = Gson().fromJson(FakeConst.jsonPopularMovies, Response::class.java)
        val response = MutableLiveData<Response>()
        response.value = fakeData

        whenever(repository.getData(1, BuildConfig.movieUrl, BuildConfig.popularUrl)).thenReturn(response)

        val observer = mock<Observer<Response>>()
        viewModel.getMoviePopular(1).observeForever(observer)

        verify(observer).onChanged(fakeData)
    }

    @Test
    fun getTvPopular(){
        val fakeData = Gson().fromJson(FakeConst.jsonPopularTv, Response::class.java)
        val response = MutableLiveData<Response>()
        response.value = fakeData

        whenever(repository.getData(1, BuildConfig.tvUrl, BuildConfig.popularUrl)).thenReturn(response)

        val observer = mock<Observer<Response>>()
        viewModel.getTvPopular(1).observeForever(observer)

        verify(observer).onChanged(fakeData)
    }
}