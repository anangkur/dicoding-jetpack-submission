package com.anangkur.jetpacksubmission1.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import androidx.viewpager.widget.ViewPager
import org.hamcrest.Matcher
import org.hamcrest.core.Is

class ViewPagerItemCountAssertion(private val matcher: Matcher<Int>) :
    ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as ViewPager
        val adapter = recyclerView.adapter
        ViewMatchers.assertThat(adapter?.count, matcher)
    }

    companion object {

        fun withItemCount(expectedCount: Int): RecyclerViewItemCountAssertion {
            return withItemCount(Is.`is`(expectedCount))
        }

        fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertion {
            return RecyclerViewItemCountAssertion(matcher)
        }
    }
}