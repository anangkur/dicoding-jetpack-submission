package com.anangkur.jetpacksubmission1.feature.favourite

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.anangkur.jetpacksubmission1.R
import com.anangkur.jetpacksubmission1.utils.EspressoIdlingResource
import com.anangkur.jetpacksubmission1.utils.ViewPagerItemCountAssertion
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavouriteActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule<FavouriteActivity>(FavouriteActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun checkTabLayout(){
        Espresso.onView(ViewMatchers.withId(R.id.vp_fav)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            check(ViewPagerItemCountAssertion(Matchers.greaterThanOrEqualTo(2)))
        }
    }
}