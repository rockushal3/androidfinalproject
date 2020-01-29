package com.example.journey_mate.router;

import com.example.journey_mate.model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserRoute {
    @POST("login")
    Call<User> userLogin(@Body User user);

    @POST("createUser")
    Call<Void> userRegister(@Body User user);

    @GET("checkLogin")
    Call<User> checkLogin(@Header("Authorization") String auth);

    @GET("getuserbyemail/{email}")
    Call<User> checkEmail(@Path("email") String email);

    @PUT("updateUser/{id}")
    Call<Void> updateProfile(@Path("id") String id,
                              @Body User user);
    @GET("findUserById/{id}")
    Call<User> finduserbyid(@Path("id") String id);

    @Multipart //for image
    @PUT("updateCover/{id}")
    Call<Void> updatecover(@Path("id") String id,@Part MultipartBody.Part img); //image file data type MultipartBody

    @Multipart //for image
    @PUT("updateProfile/{id}")
    Call<Void> updateprofilepic(@Path("id") String id,@Part MultipartBody.Part img); //image file data type MultipartBody
}
