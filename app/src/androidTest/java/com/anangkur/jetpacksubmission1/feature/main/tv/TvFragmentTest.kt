package com.anangkur.jetpacksubmission1.feature.main.tv

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.anangkur.jetpacksubmission1.utils.EspressoIdlingResource
import com.anangkur.jetpacksubmission1.utils.RecyclerViewItemCountAssertion
import org.junit.Test
import org.junit.Rule
import com.android21buttons.fragmenttestrule.FragmentTestRule
import org.hamcrest.Matchers.greaterThan
import org.junit.After
import org.junit.Before

class TvFragmentTest {

    @get:Rule var fragmentTestRule: FragmentTestRule<*, TvFragment> = FragmentTestRule.create(TvFragment::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun teardown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun checkRecyclerViewDataIsMoreThan10(){
        onView(withId(com.anangkur.jetpacksubmission1.R.id.recycler_tv_popular)).apply {
            check(matches(isDisplayed()))
            check(RecyclerViewItemCountAssertion(greaterThan(10)))
        }

        onView(withId(com.anangkur.jetpacksubmission1.R.id.recycler_tv_new)).apply {
            check(matches(isDisplayed()))
            check(RecyclerViewItemCountAssertion(greaterThan(10)))
        }

        onView(withId(com.anangkur.jetpacksubmission1.R.id.recycler_tv_rating)).apply {
            check(matches(isDisplayed()))
            check(RecyclerViewItemCountAssertion(greaterThan(10)))
        }
    }
}