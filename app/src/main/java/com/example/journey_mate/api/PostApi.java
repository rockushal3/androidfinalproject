package com.example.journey_mate.api;

import com.example.journey_mate.model.User;
import com.example.journey_mate.router.PostRoute;
import com.example.journey_mate.router.UserRoute;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class PostApi {

    //for registration function add data to api

    PostRoute postRoute = Retro.getInstance()
            .create(PostRoute.class);
    //variable to check the function
    boolean postcreate = false;
    public boolean CreatePostByUser(Multipart image,String caption){
        Call<Void> postCall = postRoute.createPost(image,caption, UserApi.loginUserDetail.get_id());
        Strict.StrictMode();
        try {
            Response<Void> postResponse = postCall.execute();
            if(postResponse.isSuccessful()){
                postcreate = true;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return postcreate;
    }
}
