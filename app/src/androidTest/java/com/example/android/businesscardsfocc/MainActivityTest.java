package com.example.android.businesscardsfocc;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void verifyBusinessNameTest() {

        ViewInteraction textView = onView(
                allOf(withText("San Francisco Outrigger Canoe Club"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("San Francisco Outrigger Canoe Club")));

        onView(withId(R.id.businessName)).check(matches(withText("San Francisco Outrigger Canoe Club")));
    }

    @Test
    public void imagePresentTest() {
        ViewInteraction imageView = onView(
                allOf(withId(R.id.image),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));
    }

    @Test
    public void verifyAddressTest() {
        onView(withId(R.id.address)).check(matches(withText("610 Mason Street\nThe Presidio\nSan Francisco, California")));
    }

    @Test
    public void verifyUrlTest() {
        onView(withId(R.id.url)).check(matches(withText("http://www.sfocc.org/")));
    }

    @Test
    public void verifyContactInfoTest() {
        onView(withId(R.id.contactInfo)).check(matches(withText("info@sfocc.org")));
    }

    @Test
    public void verifyHoursOfOperationTest() {
        onView(withId(R.id.hours)).check(matches(withText("Tuesday, Thursday 6pm & Saturday 8am")));
    }



    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
