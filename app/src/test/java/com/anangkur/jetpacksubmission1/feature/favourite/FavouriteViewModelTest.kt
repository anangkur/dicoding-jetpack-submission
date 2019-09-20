package com.anangkur.jetpacksubmission1.feature.favourite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.util.FakeConst
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class FavouriteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavouriteViewModel
    @Mock private lateinit var repository: Repository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = FavouriteViewModel(repository)
    }

    @Test
    fun getAllDataMoviePaged() {
        val response = MutableLiveData<PagedList<Result>>()
        val pagedList = mock<PagedList<Result>>()
        response.value = pagedList

        whenever(repository.getAllResultPaged(FakeConst.TYPE_MOVIE)).thenReturn(response)

        val observer = mock<Observer<PagedList<Result>>>()

        viewModel.getAllDataMoviePaged().observeForever(observer)

        verify(observer).onChanged(pagedList)
    }

    @Test
    fun getAllDataTvPaged() {
        val response = MutableLiveData<PagedList<Result>>()
        val pagedList = mock<PagedList<Result>>()
        response.value = pagedList

        whenever(repository.getAllResultPaged(FakeConst.TYPE_TV)).thenReturn(response)

        val observer = mock<Observer<PagedList<Result>>>()

        viewModel.getAllDataTvPaged().observeForever(observer)

        verify(observer).onChanged(pagedList)
    }
}