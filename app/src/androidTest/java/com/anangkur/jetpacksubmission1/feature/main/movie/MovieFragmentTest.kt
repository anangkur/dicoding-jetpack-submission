package com.anangkur.jetpacksubmission1.feature.main.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.anangkur.jetpacksubmission1.R
import com.anangkur.jetpacksubmission1.utils.RecyclerViewItemCountAssertion
import com.android21buttons.fragmenttestrule.FragmentTestRule
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.Rule

class MovieFragmentTest {

    @get:Rule var fragmentTestRule: FragmentTestRule<*, MovieFragment> = FragmentTestRule.create(MovieFragment::class.java)

    @Test
    fun checkRecyclerViewDataIsMoreThan10(){
        onView(ViewMatchers.withId(R.id.recycler_vertical)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            check(RecyclerViewItemCountAssertion(Matchers.greaterThan(10)))
        }

        onView(ViewMatchers.withId(R.id.recycler_horizontal)).apply {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            check(RecyclerViewItemCountAssertion(Matchers.greaterThan(10)))
        }
    }
}