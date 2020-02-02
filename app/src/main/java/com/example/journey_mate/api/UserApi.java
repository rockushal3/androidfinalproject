package com.example.journey_mate.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.journey_mate.model.User;
import com.example.journey_mate.router.UserRoute;

import java.io.IOException;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class UserApi {
    UserRoute userRoute = Retro.getInstance()
            .create(UserRoute.class);
    //variable to check the function
    boolean isloggedIn,isAlreadyLogin,userregister,checkemailreg,checkprofile,updatecoverimage = false;
    public static User loginUserDetail=null;

    //for login function with api data
    public boolean userLogin(User apiUser, Context context){
        Call<User> userCall = userRoute.userLogin(apiUser);
        Strict.StrictMode();
        try {
            Response<User> loginResponse = userCall.execute();
            if(loginResponse.isSuccessful()){
                isloggedIn = true;
                Retro.token += loginResponse.body().getToken();
                loginUserDetail = loginResponse.body();
                SharedPreferences sharedPreferences = context.getSharedPreferences("User",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Token",Retro.token);
                editor.commit();

            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return isloggedIn;
    }

    //for registration function add data to api

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

    // check login status
    public boolean checkLoginStatus(String token){
        Call<User> userCall = userRoute.checkLogin(token);
        Strict.StrictMode();
        try {
            Response<User> loginResponse = userCall.execute();
            if(loginResponse.isSuccessful()){
                loginUserDetail = loginResponse.body();
                isAlreadyLogin = true;
                Retro.token = token;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return isAlreadyLogin;
    }

    //check email validation
    public boolean checkemail(String email){
        Call<User> userCall = userRoute.checkEmail(email);
        Strict.StrictMode();
        try {
            Response<User> checkresponse = userCall.execute();
            if(checkresponse.isSuccessful()){
                checkemailreg=true;
            }
        } catch (IOException e) {
            System.out.println(e);
            checkemailreg=false;
        }
        return checkemailreg;
    }

    //get data of user from api with id
    public User getuserbyid(String id){
        Call<User> userCall = userRoute.finduserbyid(id);
        User userdetail = null;
        Strict.StrictMode();
        try {
            Response<User> checkresponse = userCall.execute();
            if(checkresponse.isSuccessful()){
                userdetail = checkresponse.body();
            }
        
        } catch (IOException e) {
            System.out.println(e);
        }
        return userdetail;
    }

    //update profile detail
    public boolean updateProfile(User user){
        Call<Void> userCall = userRoute.updateProfile(loginUserDetail.get_id(),user);
        Strict.StrictMode();
        try {
            Response<Void> checkresponse = userCall.execute();
            System.out.println(checkresponse.isSuccessful());
            if(checkresponse.isSuccessful()){
                checkprofile=true;
                loginUserDetail = getuserbyid(loginUserDetail.get_id());
            }
            else {
                checkprofile=false;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return checkprofile;
    }

    //update profile detail
    public boolean updatepassword(User User){
        Call<Void> userCall = userRoute.updateProfile(loginUserDetail.get_id(),User);
        boolean checkpassword =false;
        Strict.StrictMode();
        try {
            Response<Void> checkresponse = userCall.execute();
            System.out.println(checkresponse.isSuccessful());
            if(checkresponse.isSuccessful()){
                checkpassword=true;
                loginUserDetail = getuserbyid(loginUserDetail.get_id());
            }
            else {
                checkpassword=false;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return checkpassword;
    }

    //update cover picture
    public boolean updateCoverPic(MultipartBody.Part image){
        Call<Void> userCall = userRoute.updatecover(loginUserDetail.get_id(),image);
        Strict.StrictMode();
        try {
            Response<Void> checkresponse = userCall.execute();
            if(checkresponse.isSuccessful()){
                updatecoverimage=true;
                loginUserDetail = getuserbyid(loginUserDetail.get_id());
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return checkprofile;
    }

    //update profile picture
    public boolean updateProfilePic(MultipartBody.Part image){
        Call<Void> userCall = userRoute.updateprofilepic(loginUserDetail.get_id(),image);
        Strict.StrictMode();
        try {
            Response<Void> checkresponse = userCall.execute();
            System.out.println(checkresponse.isSuccessful());
            if(checkresponse.isSuccessful()){
                updatecoverimage=true;
                loginUserDetail = getuserbyid(loginUserDetail.get_id());
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return checkprofile;
    }

}
