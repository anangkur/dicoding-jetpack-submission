package com.anangkur.jetpacksubmission1.feature.favourite.movie

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.anangkur.jetpacksubmission1.R
import com.anangkur.jetpacksubmission1.feature.favourite.tv.FavouriteTvFragment
import com.anangkur.jetpacksubmission1.utils.EspressoIdlingResource
import com.anangkur.jetpacksubmission1.utils.RecyclerViewItemCountAssertion
import com.android21buttons.fragmenttestrule.FragmentTestRule
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavouriteMovieFragmentTest {

    @get:Rule
    var fragmentTestRule: FragmentTestRule<*, FavouriteMovieFragment> = FragmentTestRule.create(FavouriteMovieFragment::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun checkRecyclerViewDataIsMoreThan0(){
        Espresso.onView(ViewMatchers.withId(R.id.recycler_fav)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            check(RecyclerViewItemCountAssertion(Matchers.greaterThanOrEqualTo(0)))
        }
    }
}