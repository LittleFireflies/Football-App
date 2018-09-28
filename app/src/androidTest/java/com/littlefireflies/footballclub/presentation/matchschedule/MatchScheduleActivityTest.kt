package com.littlefireflies.footballclub.presentation.matchschedule

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.littlefireflies.footballclub.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by widyarso.purnomo on 12/09/2018.
 */
@RunWith(AndroidJUnit4::class)
class MatchScheduleActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MatchScheduleActivity::class.java)

    @Test
    fun testAppBehaviour() {
        Thread.sleep(2000)

        onView(withId(rvPrevMatch)).check(matches(isDisplayed()))
        onView(withId(rvPrevMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        onView(withId(rvPrevMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click()))

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to favorite")).check(matches(isDisplayed()))
        pressBack()

        onView(withId(bottomNavigationView)).check(matches(isDisplayed()))
        onView(withId(action_next)).perform(click())
        onView(withId(rvNextMatch)).check(matches(isDisplayed()))

        onView(withId(action_favorite)).perform(click())
        onView(withId(rvFavorite)).check(matches(isDisplayed()))
        onView(withId(rvFavorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Removed from favorite")).check(matches(isDisplayed()))
        pressBack()
    }
}