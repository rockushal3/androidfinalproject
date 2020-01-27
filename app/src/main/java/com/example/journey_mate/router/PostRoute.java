package com.example.journey_mate.router;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PostRoute {
    @Multipart //for image
    @POST("createpost")
    Call<Void> createPost(@Part MultipartBody.Part img, @Part("caption") String caption, @Part("user_id") String user_id); //image file data type MultipartBody

}
