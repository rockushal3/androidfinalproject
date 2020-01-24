package com.example.journey_mate.api;

import com.example.journey_mate.model.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class UserApi {
    UserRoute userRoute = Retro.getInstance()
            .create(UserRoute.class);
    boolean isloggedIn,isAlreadyLogin,userregister = false;

    public boolean userLogin(User apiUser){
        Call<User> userCall = userRoute.userLogin(apiUser);
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

    public boolean userRegistration(User apiUser){
        Call<Void> userCall = userRoute.userRegister(apiUser);
        Strict.StrictMode();
        try {
            Response<Void> registerResponse = userCall.execute();
            if(registerResponse.isSuccessful()){
                userregister = true;
                Retro.token += registerResponse.body().getToken();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return userregister;
    }

    public boolean checkLoginStatus(){
        Call<Void> userCall = userRoute.checkLogin(Retro.token);
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
