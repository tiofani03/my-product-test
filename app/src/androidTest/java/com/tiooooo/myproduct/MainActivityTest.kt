package com.tiooooo.myproduct

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.tiooooo.myproduct.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.anyOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun checkAllButtonIsAvailable() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnProblem1))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btnProblem2))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btnProblem3))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testProblem1() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnProblem1)).perform(click())

        onView(withId(R.id.rvOrder))
            .check(matches(isDisplayed()))

        onView(withId(R.id.btnSeeResult)).perform(click())

        onView(withText("Result Suggestions"))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rvResult))
            .check(matches(isDisplayed()))

        onView(withId(R.id.ivClose)).perform(click())

        onView(withId(R.id.tvTotal))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testProblem2() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnProblem2)).perform(click())

        onView(withId(R.id.edtFreeText))
            .check(matches(isDisplayed()))

        onView(withId(R.id.edtFreeText)).perform(clearText())
            .perform(typeText("test kata"), closeSoftKeyboard())

        onView(withId(R.id.tvResult))
            .check(matches(isDisplayed()))

        onView(anyOf(withText("Positive Review"), withText("Negative Review")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testProblem3() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnProblem3)).perform(click())

        onView(withId(R.id.rvReviewProduct))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvFilterByRating)).perform(click())
        onView(withText("No Filter"))
            .check(matches(isDisplayed()))

        onView(withId(R.id.ivClose)).perform(click())

        onView(withId(R.id.tvFilterByProduct)).perform(click())
        onView(withText("All Product"))
            .check(matches(isDisplayed()))

        onView(withId(R.id.ivClose)).perform(click())

        onView(withId(R.id.tvFilterByProduct)).perform(click())
        onView(withText("ProductA")).perform(click())
    }
}
