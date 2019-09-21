package com.anangkur.jetpacksubmission1.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
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
    private val fakeData = Result(
        adult = false,
        vote_average = 7.1f,
        video = false,
        title = "Spider-Man: Far from Home",
        release_date = "2019-07-02",
        poster_path = "/2cAc4qH9Uh2NtSujJ90fIAMrw7T.jpg",
        popularity = 355.443,
        overview = "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
        original_title = "Spider-Man: Far from Home",
        original_name = "Spider-Man: Far from Home",
        original_language = "en",
        name = "Spider-Man: Far from Home",
        id = 429617,
        genre_ids = listOf(28, 12, 878, 35, 10749),
        backdrop_path = "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg",
        vote_count = 115,
        favourite = "false",
        type = 1
    )

    @Mock private lateinit var dataSourceFactory: DataSource.Factory<Int, Result>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        repository = FakeRepository(remoteRepository, localRepository)
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

    @Test
    fun getResultById(){
        doAnswer { invocation ->
            (invocation.arguments[1] as LocalRepository.GetResultByIdCallback).onDataReceived(fakeData)
            null
        }.whenever(localRepository).getResultById(eq(429617), any())

        val result = LiveDataTestUtil.getValue(repository.getResultById(429617))

        verify(localRepository, times(1)).getResultById(eq(429617), any())

        assertNotNull(result)
    }
}