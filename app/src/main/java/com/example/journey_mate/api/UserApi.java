package com.example.journey_mate.api;

import com.example.journey_mate.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("login")
    Call<User> userLogin(@Body User user);


}
