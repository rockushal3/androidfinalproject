package com.example.journey_mate.adaptor;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.journey_mate.R;
import com.example.journey_mate.api.FriendRequestApi;
import com.example.journey_mate.api.Retro;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.controller.UserProfile;
import com.example.journey_mate.model.FriendRelationResponce;
import com.example.journey_mate.model.SearchResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdaptor extends RecyclerView.Adapter<SearchAdaptor.FriendListHolder>{
    Context context;
    List<SearchResponse> friendlist;

    public SearchAdaptor(Context context, List<SearchResponse> friendlist) {
        this.context = context;
        this.friendlist = friendlist;
    }

    @NonNull
    @Override
    public FriendListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendlist, parent,false);
       FriendListHolder friendListAdaptor = new FriendListHolder(view);
        return friendListAdaptor;
    }

    @Override
    public void onBindViewHolder(@NonNull final FriendListHolder holder, int position) {
        final SearchResponse friend = friendlist.get(position);
        if (friend.getUser_id().get_id().equals(UserApi.loginUserDetail.get_id())){
            holder.username.setText(friend.getUser_id().getName());
            holder.address.setText(friend.getUser_id().getAddress());
            if(!friend.getUser_id().getImage().isEmpty()) {
                Picasso.with(context).load(Retro.IMG_URL + friend.getUser_id().getImage()).into(holder.profilepic);
            }
            holder.username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserProfile.class);
                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View,String>(holder.profilepic,"profileImage");
                    pairs[1] = new Pair<View,String>(holder.username,"profileName");
                    intent.putExtra("Id", friend.getUser_id().get_id());
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                            pairs);
                    context.startActivity(intent,options.toBundle());

                }
            });
        }
        else{
            holder.username.setText(friend.getUser_id().getName());
            holder.address.setText(friend.getUser_id().getAddress());
            if(!friend.getUser_id().getImage().isEmpty()) {
                Picasso.with(context).load(Retro.IMG_URL + friend.getUser_id().getImage()).into(holder.profilepic);
            }
            holder.username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserProfile.class);
                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View,String>(holder.profilepic,"profileImage");
                    pairs[1] = new Pair<View,String>(holder.username,"profileName");
                    intent.putExtra("Id", friend.getUser_id().get_id());
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                            pairs);
                    context.startActivity(intent,options.toBundle());

                }
            });
        }



    }

    @Override
    public int getItemCount() {
        return friendlist.size();
    }


    public class FriendListHolder extends RecyclerView.ViewHolder{
        CircleImageView profilepic;
        TextView username,address;

        public FriendListHolder(@NonNull View itemView) {
            super(itemView);
            profilepic= itemView.findViewById(R.id.profile_image);
            username = itemView.findViewById(R.id.menu_username);
            address = itemView.findViewById(R.id.address);
        }
    }
}
