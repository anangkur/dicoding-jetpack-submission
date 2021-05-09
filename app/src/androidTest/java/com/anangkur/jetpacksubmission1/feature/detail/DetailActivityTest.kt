package com.anangkur.jetpacksubmission1.feature.detail

import android.content.Intent
import android.widget.RatingBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.anangkur.jetpacksubmission1.R
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.utils.EspressoIdlingResource
import com.anangkur.jetpacksubmission1.utils.FakeConst
import com.anangkur.jetpacksubmission1.utils.Utils
import org.junit.*

class DetailActivityTest {

    val fakeData = Result(
        adult = false,
        vote_average = 7f,
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

    @get:Rule val activityRule = object: ActivityTestRule<DetailActivity>(DetailActivity::class.java){
        override fun getActivityIntent(): Intent {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val result = Intent(targetContext, DetailActivity::class.java)
            result.putExtra(FakeConst.EXTRA_DETAIL, fakeData)
            return result
        }
    }

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun teardown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun checkShowDataSameWithIntent(){
        onView(withId(R.id.iv_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_title)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(fakeData.original_title?:fakeData.original_name)))
        }

        onView(withId(R.id.rating)).check(matches(isDisplayed()))
        Assert.assertEquals(
            Utils.nomalizeRating(fakeData.vote_average),
            activityRule.activity.findViewById<RatingBar>(R.id.rating).rating
        )

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