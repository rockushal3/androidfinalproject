package com.example.journey_mate;

import com.example.journey_mate.controller.Home;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class searchTest {
    @Rule
    public ActivityTestRule<Home> searchrule = new ActivityTestRule<>(Home.class);
    String searchquery = "kathmandu";
    String email = "rockushal3@gmail.com";
    String password = "kushal.com";

    @Before
    public void LoginUserTest() {
        onView(withId(R.id.login_email)).perform(typeText(email));
        onView(withId(R.id.login_password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
    }
    @Test
    public void SearchTest() {
        onView(withId(R.id.btn_search)).perform(click());
        onView(withId(R.id.search_friend)).perform(typeText(searchquery));
        closeSoftKeyboard();
    }
}
