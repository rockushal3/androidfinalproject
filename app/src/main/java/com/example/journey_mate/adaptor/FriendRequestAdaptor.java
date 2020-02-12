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
import com.example.journey_mate.model.FriendRelationResponce;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class FriendRequestAdaptor extends RecyclerView.Adapter<FriendRequestAdaptor.FriendRequestHolder>{

    Context context;
    List<FriendRelationResponce> friendrequestlist;
    FriendRequestApi friendRequestApi = new FriendRequestApi();
    Fragment fragment;

    public FriendRequestAdaptor(Context context, List<FriendRelationResponce> friendrequestlist,Fragment fragment) {
        this.context = context;
        this.friendrequestlist = friendrequestlist;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public FriendRequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.requestedfriend, parent,false);
        FriendRequestHolder friendRequestAdaptor = new FriendRequestHolder(view);
        return friendRequestAdaptor;
    }

    @Override
    public void onBindViewHolder(@NonNull final FriendRequestHolder holder, int position) {
        final FriendRelationResponce friend = friendrequestlist.get(position);
        if(!friend.getUser_id_1().getImage().isEmpty()) {
            Picasso.with(context).load(Retro.IMG_URL + friend.getUser_id_1().getImage()).into(holder.profilepic);
        }
        holder.username.setText(friend.getUser_id_1().getName());
        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserProfile.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(holder.profilepic,"profileImage");
                pairs[1] = new Pair<View,String>(holder.username,"profileName");
                intent.putExtra("Id", friend.getUser_id_1().get_id());
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                        pairs);
                context.startActivity(intent,options.toBundle());

            }
        });

        holder.confirm_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(friendRequestApi.AcceptFriend(friend.get_id())) {
                    Toast.makeText(context, friend.getUser_id_1().getName() + " and " + UserApi.loginUserDetail.getName() + " are Friends", Toast.LENGTH_SHORT).show();
                    FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                    ft.detach(fragment).attach(fragment).commit();
                }


            }
        });

        holder.delete_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(friendRequestApi.deletePost(friend.get_id())) {
                    Toast.makeText(context, friend.getUser_id_1().getName() + " friend Request Has been deleted" , Toast.LENGTH_SHORT).show();
                    FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                    ft.detach(fragment).attach(fragment).commit();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return friendrequestlist.size();
    }

    public class FriendRequestHolder extends RecyclerView.ViewHolder{
        CircleImageView profilepic;
        TextView username;
        Button delete_request,confirm_request;

        public FriendRequestHolder(@NonNull View itemView) {
            super(itemView);
            profilepic= itemView.findViewById(R.id.profile_image);
            username = itemView.findViewById(R.id.menu_username);
            delete_request=itemView.findViewById(R.id.delete_request);
            confirm_request=itemView.findViewById(R.id.confirm_button);
        }
    }
}
