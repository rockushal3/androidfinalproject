package com.example.journey_mate.api;

import com.example.journey_mate.model.PostResponce;
import com.example.journey_mate.router.PostRoute;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class PostApi {

    //for registration function add data to api

    PostRoute postRoute = Retro.getInstance()
            .create(PostRoute.class);
    //variable to check the function
    boolean postcreate = false;

    public boolean CreatePostByUser(MultipartBody.Part image, String caption) {
        System.out.println(UserApi.loginUserDetail.get_id());
        RequestBody postcaption = RequestBody.create(MediaType.parse("text/plain"), caption);
        RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"), UserApi.loginUserDetail.get_id());

        Call<Void> postCall = postRoute.createPost(image, postcaption, user_id);
        Strict.StrictMode();
        try {
            Response<Void> postResponse = postCall.execute();
            if (postResponse.isSuccessful()) {
                postcreate = true;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return postcreate;
    }

    public List<PostResponce> findpost() {
        List<PostResponce> postlist = null;
        Call<List<PostResponce>> postCall = postRoute.findPost();
        Strict.StrictMode();
        try {
            Response<List<PostResponce>> postResponse = postCall.execute();
            postlist = postResponse.body();


        } catch (IOException e) {
            System.out.println(e);
        }
        return postlist;
    }

    public List<PostResponce> findpostByuserId(String Id) {
        List<PostResponce> postlist = null;
        Call<List<PostResponce>> postCall = postRoute.findPostByUserId(Id);
        Strict.StrictMode();
        try {
            Response<List<PostResponce>> postResponse = postCall.execute();
            postlist = postResponse.body();
        } catch (IOException e) {
            System.out.println(e);
        }
        return postlist;
    }

    public Boolean deletePost(String Id) {
        Boolean checkdelete = false;
        Call<Void> postCall = postRoute.deletePost(Id);
        Strict.StrictMode();
        try {
            Response<Void> postResponse = postCall.execute();
            if(postResponse.isSuccessful()){
                checkdelete=true;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return checkdelete;
    }

}
