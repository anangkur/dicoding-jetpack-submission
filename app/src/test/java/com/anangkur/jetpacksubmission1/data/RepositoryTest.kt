package com.anangkur.jetpacksubmission1.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.anangkur.jetpacksubmission1.BuildConfig
import com.anangkur.jetpacksubmission1.data.local.LocalRepository
import com.anangkur.jetpacksubmission1.data.model.Response
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.data.remote.RemoteRepository
import com.anangkur.jetpacksubmission1.util.FakeConst
import com.anangkur.jetpacksubmission1.util.FakeRepository
import com.anangkur.jetpacksubmission1.util.LiveDataTestUtil
import com.anangkur.jetpacksubmission1.util.PagedListUtil
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RepositoryTest {

    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock private lateinit var remoteRepository: RemoteRepository
    @Mock private lateinit var localRepository: LocalRepository
    private lateinit var repository: FakeRepository

    private val moviePopularResponse = Gson().fromJson(FakeConst.jsonPopularMovies, Response::class.java)
    private val tvPopularresponse = Gson().fromJson(FakeConst.jsonPopularTv, Response::class.java)

    @Mock private lateinit var dataSourceFactory: DataSource.Factory<Int, Result>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        repository =
            FakeRepository(remoteRepository, localRepository)
    }

    @Test
    fun getDataMovie() {
        doAnswer { invocation ->
            (invocation.arguments[3] as RemoteRepository.LoadMovieCallback).onDataReceived(moviePopularResponse)
            null
        }.whenever(remoteRepository).getData(eq(1), eq(BuildConfig.movieUrl), eq(BuildConfig.popularUrl), any())

        val result = LiveDataTestUtil.getValue(repository.getData(1, BuildConfig.movieUrl, BuildConfig.popularUrl))

        verify(remoteRepository, times(1)).getData(eq(1), eq(BuildConfig.movieUrl), eq(BuildConfig.popularUrl), any())

        assertNotNull(result)
        assertEquals(moviePopularResponse.results.size, result.results.size)
    }

    @Test
    fun getDataTv() {
        doAnswer { invocation ->
            (invocation.arguments[3] as RemoteRepository.LoadMovieCallback).onDataReceived(tvPopularresponse)
            null
        }.whenever(remoteRepository).getData(eq(1), eq(BuildConfig.tvUrl), eq(BuildConfig.popularUrl), any())

        val result = LiveDataTestUtil.getValue(repository.getData(1, BuildConfig.tvUrl, BuildConfig.popularUrl))

        verify(remoteRepository, times(1)).getData(eq(1), eq(BuildConfig.tvUrl), eq(BuildConfig.popularUrl), any())

        assertNotNull(result)
        assertEquals(tvPopularresponse.results.size, result.results.size)
    }

    @Test
    fun getAllResultPagedMovie(){
        whenever(localRepository.getAllResultPaged(FakeConst.TYPE_MOVIE)).thenReturn(dataSourceFactory)
        repository.getAllResultPaged(FakeConst.TYPE_MOVIE)
        val result = PagedListUtil.mockPagedList(moviePopularResponse.results)

        verify(localRepository).getAllResultPaged(FakeConst.TYPE_MOVIE)
        assertNotNull(result)
        assertEquals(moviePopularResponse.results.size, result.size)
    }

    @Test
    fun getAllResultPagedTv(){
        whenever(localRepository.getAllResultPaged(FakeConst.TYPE_TV)).thenReturn(dataSourceFactory)
        repository.getAllResultPaged(FakeConst.TYPE_TV)
        val result = PagedListUtil.mockPagedList(tvPopularresponse.results)

        verify(localRepository).getAllResultPaged(FakeConst.TYPE_TV)
        assertNotNull(result)
        assertEquals(moviePopularResponse.results.size, result.size)
    }
}