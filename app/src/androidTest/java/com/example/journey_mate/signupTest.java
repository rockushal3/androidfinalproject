package com.example.journey_mate;

import com.example.journey_mate.controller.registration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class signupTest {
    @Rule
    public ActivityTestRule<registration> rule = new ActivityTestRule<>(registration.class);
    String email = "test@gmail.com";
    String password = "kushal.com";
    String fname ="test";
    String lname="test";
    String phone="9808808801";


    @Test
    public void LoginUserTest() {
        onView(withId(R.id.startsignup)).perform(click());
        onView(withId(R.id.fname)).perform(typeText(fname));
        onView(withId(R.id.lname)).perform(typeText(lname));
        onView(withId(R.id.btnTakeName)).perform(click());
        onView(withId(R.id.btnTakeDob)).perform(click());
        onView(withId(R.id.rbMale)).perform(click());
        onView(withId(R.id.btnTakeGender)).perform(click());
        onView(withId(R.id.etPhone)).perform(typeText(phone));
        onView(withId(R.id.btnTakePhone)).perform(click());
        onView(withId(R.id.etEmail)).perform(typeText(email));
        onView(withId(R.id.btnTakeEmail)).perform(click());
        onView(withId(R.id.etPassword)).perform(typeText(password));
        onView(withId(R.id.btnTakePassword)).perform(click());
        onView(withId(R.id.btnTakeSignup)).perform(click());

    }
}
