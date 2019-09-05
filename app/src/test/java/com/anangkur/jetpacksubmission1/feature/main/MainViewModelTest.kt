package com.anangkur.jetpacksubmission1.feature.main

import android.app.Application
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.utils.Const
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    @Mock private lateinit var application: Application
    @Mock private lateinit var repository: Repository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(application, repository)
    }
}