package com.example.journey_mate.router;


import com.example.journey_mate.model.PostResponce;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface PostRoute {
    @Multipart //for image
    @POST("createpost")
    Call<Void> createPost(@Part MultipartBody.Part img, @Part("caption") RequestBody caption, @Part("user_id") RequestBody user_id); //image file data type MultipartBody

    @GET("findpost")
    Call<List<PostResponce>> findPost();

    @GET("findpostByUserId/{id}")
    Call<List<PostResponce>> findPostByUserId(@Path("id") String id);

    @DELETE("deletepost/{id}")
    Call<Void> deletePost(@Path("id") String id);


}
