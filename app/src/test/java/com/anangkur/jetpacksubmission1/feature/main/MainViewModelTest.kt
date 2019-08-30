package com.anangkur.jetpacksubmission1.feature.main

import android.app.Application
import com.anangkur.jetpacksubmission1.utils.Const
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    @Mock private lateinit var application: Application

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(application)
    }

    @Test
    fun createDataTvPopular() {
        val data = viewModel.createDataTvPopular(Const.jsonPopularTv)
        assertNotNull(data)
        assert(data.size > 10)
    }

    @Test
    fun createDataMoviePopular() {
        val data = viewModel.createDataMoviePopular(Const.jsonPopularMovies)
        assertNotNull(data)
        assert(data.size > 10)
    }
}