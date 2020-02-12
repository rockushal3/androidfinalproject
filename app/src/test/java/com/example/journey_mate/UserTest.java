package com.example.journey_mate;


import com.example.journey_mate.api.Retro;
import com.example.journey_mate.model.SearchResponse;
import com.example.journey_mate.model.User;
import com.example.journey_mate.router.TripRoute;
import com.example.journey_mate.router.UserRoute;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertTrue;

public class UserTest {
    UserRoute userRoute = Retro.getInstance()
            .create(UserRoute.class);

    @Test
    public void test_userRegistration() {
        Call<Void> userCall = userRoute.userRegister(new User("kushal", "jorpati", "9863837700", "Male", "20/12/1996", "test@gmail.com", "kushal", "", "", ""));
        try {
            Response<Void> registerResponse = userCall.execute();
            if (registerResponse.isSuccessful()) {
                assertTrue(registerResponse.isSuccessful());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void test_userLogin() {
        User user = new User("test@gmail.com", "kushal");
        Call<User> userCall = userRoute.userLogin(user);
        try {
            Response<User> loginResponse = userCall.execute();
            if (loginResponse.isSuccessful()) {
                assertTrue(loginResponse.isSuccessful());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void test_searchTrip() {
        TripRoute tripRoute = Retro.getInstance()
                .create(TripRoute.class);
        Call<List<SearchResponse>> searchcall = tripRoute.search("kath");
        try {
            Response<List<SearchResponse>> checkresponse = searchcall.execute();
            if (checkresponse.isSuccessful()) {
                assertTrue(checkresponse.isSuccessful());
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void findpost() {
        Call<User> userCall = userRoute.checkEmail("rockushal3@gmail.com");
        try {
            Response<User> checkresponse = userCall.execute();
            if (checkresponse.isSuccessful()) {
                assertTrue(checkresponse.isSuccessful());

            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void checkLoginStatus() {
        Call<User> userCall = userRoute.checkLogin("token");
        try {
            Response<User> loginResponse = userCall.execute();
            if (loginResponse.isSuccessful()) {
                assertTrue(loginResponse.isSuccessful());

            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
