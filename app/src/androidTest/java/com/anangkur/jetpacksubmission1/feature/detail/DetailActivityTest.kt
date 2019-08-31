package com.anangkur.jetpacksubmission1.feature.detail

import android.content.Intent
import androidx.test.rule.ActivityTestRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.anangkur.jetpacksubmission1.R
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.utils.FakeConst
import kotlinx.android.synthetic.main.activity_detail.view.*


class DetailActivityTest {

    val fakeData = Result(
        adult = false,
        vote_average = 7.1f,
        video = false,
        title = "Spider-Man: Far from Home",
        release_date = "2019-07-02",
        poster_path = "/2cAc4qH9Uh2NtSujJ90fIAMrw7T.jpg",
        popularity = 367.979,
        overview = "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
        original_title = "Spider-Man: Far from Home",
        original_name = "",
        original_language = "en",
        name = "",
        id = 429617,
        genre_ids = listOf(28, 12, 878, 35, 10749),
        backdrop_path = "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg",
        vote_count = 115
    )

    @get:Rule val activityRule = object: ActivityTestRule<DetailActivity>(DetailActivity::class.java){
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailActivity::class.java)
            result.putExtra(FakeConst.EXTRA_DETAIL, fakeData)
            return result
        }
    }

    @Test
    fun checkShowDataSameWithIntent(){
        onView(withId(R.id.iv_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_title)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(fakeData.original_title?:fakeData.original_name)))
        }

        onView(withId(R.id.rating)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_release_date)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(fakeData.release_date ?: "-")))
        }

        onView(withId(R.id.tv_language)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(fakeData.original_language)))
        }

        onView(withId(R.id.tv_popularity)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(fakeData.popularity.toString())))
        }

        onView(withId(R.id.tv_overview)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(fakeData.overview)))
        }
    }
}