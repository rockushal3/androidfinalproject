package com.example.journey_mate.adaptor;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.journey_mate.R;
import com.example.journey_mate.api.FriendRequestApi;
import com.example.journey_mate.api.Retro;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.controller.UserProfile;
import com.example.journey_mate.controller.profile;
import com.example.journey_mate.model.FriendRelationResponce;
import com.example.journey_mate.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import static java.security.AccessController.getContext;

public class FriendListAdaptor extends RecyclerView.Adapter<FriendListAdaptor.FriendListHolder> {
    Context context;
    List<FriendRelationResponce> friendlist;

    public FriendListAdaptor(Context context, List<FriendRelationResponce> friendlist) {
        this.context = context;
        this.friendlist = friendlist;
    }

    @NonNull
    @Override
    public FriendListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendlist, parent, false);
        FriendListHolder friendListAdaptor = new FriendListHolder(view);
        return friendListAdaptor;
    }

    @Override
    public void onBindViewHolder(@NonNull final FriendListHolder holder, int position) {
        final FriendRelationResponce friend = friendlist.get(position);
        if (friend.getUser_id_1().get_id().equals(UserApi.loginUserDetail.get_id())) {
            holder.username.setText(friend.getUser_id_2().getName());
            holder.address.setText(friend.getUser_id_2().getAddress());
            if (!friend.getUser_id_2().getImage().isEmpty()) {
                Picasso.with(context).load(Retro.IMG_URL + friend.getUser_id_2().getImage()).into(holder.profilepic);
            }
            holder.username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (friend.getUser_id_2().get_id().equals(UserApi.loginUserDetail.get_id())) {
                        Intent intent = new Intent(context, profile.class);
                        Pair[] pairs = new Pair[2];
                        pairs[0] = new Pair<View, String>(holder.profilepic, "profileImage");
                        pairs[1] = new Pair<View, String>(holder.username, "profileName");
                        intent.putExtra("Id", friend.getUser_id_2().get_id());
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                                pairs);
                        context.startActivity(intent, options.toBundle());
                    } else {
                        Intent intent = new Intent(context, UserProfile.class);
                        Pair[] pairs = new Pair[2];
                        pairs[0] = new Pair<View, String>(holder.profilepic, "profileImage");
                        pairs[1] = new Pair<View, String>(holder.username, "profileName");
                        intent.putExtra("Id", friend.getUser_id_2().get_id());
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                                pairs);
                        context.startActivity(intent, options.toBundle());
                    }
                }
            });
        } else {
            holder.username.setText(friend.getUser_id_1().getName());
            holder.address.setText(friend.getUser_id_1().getAddress());
            if (!friend.getUser_id_1().getImage().isEmpty()) {
                Picasso.with(context).load(Retro.IMG_URL + friend.getUser_id_1().getImage()).into(holder.profilepic);
            }
            holder.username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (friend.getUser_id_1().get_id().equals(UserApi.loginUserDetail.get_id())) {
                        Intent intent = new Intent(context, profile.class);
                        Pair[] pairs = new Pair[2];
                        pairs[0] = new Pair<View, String>(holder.profilepic, "profileImage");
                        pairs[1] = new Pair<View, String>(holder.username, "profileName");
                        intent.putExtra("Id", friend.getUser_id_1().get_id());
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                                pairs);
                        context.startActivity(intent, options.toBundle());
                    } else {
                        Intent intent = new Intent(context, UserProfile.class);
                        Pair[] pairs = new Pair[2];
                        pairs[0] = new Pair<View, String>(holder.profilepic, "profileImage");
                        pairs[1] = new Pair<View, String>(holder.username, "profileName");
                        intent.putExtra("Id", friend.getUser_id_1().get_id());
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                                pairs);
                        context.startActivity(intent, options.toBundle());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return friendlist.size();
    }

    public class FriendListHolder extends RecyclerView.ViewHolder {
        CircleImageView profilepic;
        TextView username, address;

        public FriendListHolder(@NonNull View itemView) {
            super(itemView);
            profilepic = itemView.findViewById(R.id.profile_image);
            username = itemView.findViewById(R.id.menu_username);
            address = itemView.findViewById(R.id.address);
        }
    }
}
