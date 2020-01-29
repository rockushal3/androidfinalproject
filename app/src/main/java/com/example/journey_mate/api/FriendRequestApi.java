package com.example.journey_mate.api;

import com.example.journey_mate.model.FriendRelation;
import com.example.journey_mate.model.FriendRelationResponce;
import com.example.journey_mate.router.FriendRequestRoute;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FriendRequestApi {
    FriendRequestRoute friendRequestRoute = Retro.getInstance()
            .create(FriendRequestRoute.class);
    Boolean checkFriendAccept,checksendrequest=false;

    public List<FriendRelationResponce> findrequestByuserId(){
        List<FriendRelationResponce> requestlist = null;
        Call<List<FriendRelationResponce>> postCall = friendRequestRoute.getRequest(UserApi.loginUserDetail.get_id());
        Strict.StrictMode();
        try {
            Response<List<FriendRelationResponce>> friendresponse = postCall.execute();
            requestlist=friendresponse.body();
        } catch (IOException e) {
            System.out.println(e);
        }
        return  requestlist;
    }

    public List<FriendRelationResponce> getFriendList(String Id){
        List<FriendRelationResponce> requestlist = null;
        Call<List<FriendRelationResponce>> postCall = friendRequestRoute.getFriendList(Id);
        Strict.StrictMode();
        try {
            Response<List<FriendRelationResponce>> friendresponse = postCall.execute();
            requestlist=friendresponse.body();
        } catch (IOException e) {
            System.out.println(e);
        }
        return  requestlist;
    }

    public Boolean AcceptFriend(String id){
        Call<Void> postCall = friendRequestRoute.acceptFriend(id);
        Strict.StrictMode();
        try {
            Response<Void> friendresponse = postCall.execute();
            if(friendresponse.isSuccessful()){
                checkFriendAccept=true;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return checkFriendAccept;
    }

    public Boolean sendRequest(FriendRelation friendRelation){
        Call<Void> postCall = friendRequestRoute.sendRequest(friendRelation);
        Strict.StrictMode();
        try {
            Response<Void> friendresponse = postCall.execute();
            if(friendresponse.isSuccessful()){
                checksendrequest=true;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return checksendrequest;
    }

    public FriendRelationResponce checkFriendStatus(String id){
        FriendRelationResponce requestlist = null;
        Call<FriendRelationResponce> postCall = friendRequestRoute.checkRelation(UserApi.loginUserDetail.get_id(),id);
        Strict.StrictMode();
        try {
            Response<FriendRelationResponce> friendresponse = postCall.execute();
            if(friendresponse.isSuccessful()) {
                requestlist = friendresponse.body();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return  requestlist;
    }

    public Boolean deletePost(String Id) {
        Boolean checkdelete = false;
        Call<Void> postCall =friendRequestRoute.deleteFriend(Id);
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
