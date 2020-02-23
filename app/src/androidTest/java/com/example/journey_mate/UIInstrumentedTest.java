package com.example.journey_mate;


import com.example.journey_mate.controller.Login;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class UIInstrumentedTest {
    @Rule
    public ActivityTestRule<Login> rule = new ActivityTestRule<>(Login.class);
    String email = "rockushal3@gmail.com";
    String password = "kushal.com";

    @Test
    public void LoginUserTest() {
        onView(withId(R.id.login_email)).perform(typeText(email));
        onView(withId(R.id.login_password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
    }
    @Test
    public void Search() {
        LoginUserTest();
        onView(withId(R.id.btn_search)).perform(click());
        onView(withId(R.id.search_friend)).perform(typeText("kathmandu"));
    }

    @Test
    public void ProfileEdit() {
        LoginUserTest();
        onView(withId(R.id.btn_menu)).perform(click());
        onView( withId( R.id.navView ) ).perform( NavigationViewActions.navigateTo( R.id.profile_nav ));
                onView(withId(R.id.btn_edit_profile)).perform(click());
        onView(withId(R.id.Address)).perform(typeText("kathmandu"));
        closeSoftKeyboard();
        onView(withId(R.id.update)).perform(click());

    }

    @Test
    public void viewTrip() {
        LoginUserTest();
        onView(withId(R.id.btn_menu)).perform(click());
        onView( withId( R.id.navView ) ).perform( NavigationViewActions.navigateTo( R.id.trip ));

    }


}
