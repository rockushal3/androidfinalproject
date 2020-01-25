package com.example.journey_mate.api;

import com.example.journey_mate.model.User;
import com.example.journey_mate.router.UserRoute;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class UserApi {
    UserRoute userRoute = Retro.getInstance()
            .create(UserRoute.class);
    boolean isloggedIn,isAlreadyLogin,userregister,checkemailreg = false;
    public static User loginUserDetail,checkemailuser=null;
    public boolean userLogin(User apiUser){
        Call<User> userCall = userRoute.userLogin(apiUser);
        Strict.StrictMode();
        try {
            Response<User> loginResponse = userCall.execute();
            if(loginResponse.isSuccessful()){
                isloggedIn = true;
                Retro.token += loginResponse.body().getToken();
                loginUserDetail = loginResponse.body();
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

    public boolean checkemail(String email){
        Call<User> userCall = userRoute.checkEmail(email);
        Strict.StrictMode();
        try {
            Response<User> checkresponse = userCall.execute();
            System.out.println(checkresponse.isSuccessful());
            if(checkresponse.isSuccessful()){
                checkemailreg=true;
            }
            else {
                checkemailreg=false;
            }
        } catch (IOException e) {
            System.out.println(e);
            checkemailreg=false;
        }
        return checkemailreg;
    }

    public boolean updateProfile(User user){
        Call<Void> userCall = userRoute.updateProfile(loginUserDetail.get_id(),user);
        Strict.StrictMode();
        try {
            Response<Void> checkresponse = userCall.execute();
            System.out.println(checkresponse.isSuccessful());
            if(checkresponse.isSuccessful()){
                checkemailreg=true;
            }
            else {
                checkemailreg=false;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return checkemailreg;
    }
}
