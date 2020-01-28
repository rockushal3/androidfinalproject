package com.example.journey_mate.api;

import com.example.journey_mate.model.FriendRelationResponce;
import com.example.journey_mate.router.FriendRequestRoute;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FriendRequestApi {
    FriendRequestRoute friendRequestRoute = Retro.getInstance()
            .create(FriendRequestRoute.class);

    public List<FriendRelationResponce> findrequestByuserId(){
        List<FriendRelationResponce> requestlist = null;
        Call<List<FriendRelationResponce>> postCall = friendRequestRoute.getRequest(UserApi.loginUserDetail.get_id());
        Strict.StrictMode();
        try {
            Response<List<FriendRelationResponce>> postResponse = postCall.execute();
            requestlist=postResponse.body();
        } catch (IOException e) {
            System.out.println(e);
        }
        return  requestlist;
    }
}
