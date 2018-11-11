package com.robotsoftwarestudio.footballapp.views.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.robotsoftwarestudio.footballapp.R
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {


    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {

        run {

            onView(withId(R.id.bottom_menu_matches)).perform(click())
            //give time for idling
            val idlingResource = RequestIdlingResources()
            IdlingRegistry.getInstance().register(idlingResource)

            onView(allOf(withId(R.id.recycler_main), isDisplayed()))
                    .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
                    .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        }

        run {

            delay()

            onView(withId(R.id.home_image)).check(matches(isDisplayed()))
            onView(withId(R.id.away_image)).check(matches(isDisplayed()))

            onView(withId(R.id.action_add_to_favorite)).perform(click())

            delay()

        }

        pressBack()

        run {

            onView(withId(R.id.bottom_menu_matches)).perform(click())

            onView(allOf(withId(R.id.view_page_main), isDisplayed())).perform(swipeLeft())

            delay()

            onView(allOf(withId(R.id.recycler_main), isDisplayed()))
                    .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
                    .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))


        }

        run {

            delay()

            onView(withId(R.id.home_image)).check(matches(isDisplayed()))
            onView(withId(R.id.away_image)).check(matches(isDisplayed()))

            onView(withId(R.id.action_add_to_favorite)).perform(click())

            delay()

        }

        pressBack()

        run {

            onView(withId(R.id.bottom_menu_Teams)).perform(click())

            delay()

            onView(allOf(withId(R.id.recycler_main), isDisplayed()))
                    .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
                    .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

            delay()

            onView(withId(R.id.action_add_to_favorite)).perform(click())

            delay()

            pressBack()

            delay()

        }

        run {

            onView(withId(R.id.bottom_menu_Favorites)).perform(click())

            delay()

            onView(allOf(withId(R.id.recycler_main), isDisplayed()))
                    .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
                    .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

            delay()

            onView(withId(R.id.action_add_to_favorite)).perform(click())

            delay()

            pressBack()

            delay()

        }

        run {

            onView(withId(R.id.bottom_menu_Favorites)).perform(click())

            delay()

            onView(allOf(withId(R.id.view_page_main), isDisplayed())).perform(swipeLeft())

            delay()

            onView(allOf(withId(R.id.recycler_main), isDisplayed()))
                    .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
                    .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

            delay()

            onView(withId(R.id.action_add_to_favorite)).perform(click())

            delay()

            pressBack()

            delay()

        }

    }

    private fun delay() {
        try {
            Thread.sleep(2000)
        } catch (exc: InterruptedException) {
            exc.printStackTrace()
        }
    }
}