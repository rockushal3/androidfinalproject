package com.example.journey_mate.api;

import com.example.journey_mate.model.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class UserApi {
    UserRoute fb = Retro.getInstance()
            .create(UserRoute.class);
    boolean isloggedIn,isAlreadyLogin = false;

    public boolean userLogin(User apiUser){
        Call<User> userCall = fb.userLogin(apiUser);
        Strict.StrictMode();
        try {
            Response<User> loginResponse = userCall.execute();
            if(loginResponse.isSuccessful()){
                isloggedIn = true;
                Retro.token += loginResponse.body().getToken();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return isloggedIn;
    }

    public boolean checkLoginStatus(){
        Call<Void> userCall = fb.checkLogin(Retro.token);
        Strict.StrictMode();
        try {
            Response<Void> loginResponse = userCall.execute();
            if(loginResponse.isSuccessful()){
                isAlreadyLogin = true;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return isAlreadyLogin;
    }
}
