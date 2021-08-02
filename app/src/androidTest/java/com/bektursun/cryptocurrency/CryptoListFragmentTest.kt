package com.bektursun.cryptocurrency

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.bektursun.cryptocurrency.ui.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4ClassRunner::class)
class CryptoListFragmentTest : KoinTest {

    private val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    val intentsRule = IntentsTestRule(MainActivity::class.java, true, false)

    @get:Rule
    val rule = RuleChain
        .outerRule(intentsRule)
        .around(activityTestRule)

    @Test
    fun testRV() {
        onView(withId(R.id.rv_currency)).check(matches(isDisplayed()))
    }

    @Test
    fun testCurrencyEditText() {
        onView(withId(R.id.et_search)).perform(typeText("BTC"))
        onView(withId(R.id.et_search)).check(matches(hasFocus()))
        onView(withId(R.id.et_search)).check(matches(withText("BTC")))
    }
}