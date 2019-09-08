package com.anangkur.jetpacksubmission1.feature.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
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

class MainActivityTest {

    @get:Rule val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun teardown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun checkSliderDataIsMoreThan10(){
        onView(ViewMatchers.withId(R.id.vp_slider)).apply {
            check(matches(ViewMatchers.isDisplayed()))
            check(ViewPagerItemCountAssertion(Matchers.greaterThan(10)))
        }
    }

    @Test
    fun checkTabLayout(){
        onView(ViewMatchers.withId(R.id.vp_main)).apply {
            check(matches(ViewMatchers.isDisplayed()))
            check(ViewPagerItemCountAssertion(Matchers.greaterThanOrEqualTo(2)))
        }
    }
}