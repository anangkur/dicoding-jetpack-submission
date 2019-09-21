package com.anangkur.jetpacksubmission1.feature.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.anangkur.jetpacksubmission1.data.Repository
import com.anangkur.jetpacksubmission1.data.model.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailViewModel
    @Mock
    private lateinit var repository: Repository

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

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getResultById() {
        val response = MutableLiveData<Result>()
        response.value = fakeData

        whenever(repository.getResultById(429617)).thenReturn(response)

        val observer = mock<Observer<Result>>()
        viewModel.getResultById(429617).observeForever(observer)

        verify(observer).onChanged(fakeData)
    }
}