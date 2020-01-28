package com.example.journey_mate.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.journey_mate.R;
import com.example.journey_mate.api.Retro;
import com.example.journey_mate.model.FriendRelationResponce;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class FriendRequestAdaptor extends RecyclerView.Adapter<FriendRequestAdaptor.FriendRequestHolder>{

    Context context;
    List<FriendRelationResponce> friendrequestlist;

    public FriendRequestAdaptor(Context context, List<FriendRelationResponce> friendrequestlist) {
        this.context = context;
        this.friendrequestlist = friendrequestlist;
    }

    @NonNull
    @Override
    public FriendRequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.requestedfriend, parent,false);
        FriendRequestHolder friendRequestAdaptor = new FriendRequestHolder(view);
        return friendRequestAdaptor;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestHolder holder, int position) {
        final FriendRelationResponce friend = friendrequestlist.get(position);
        if(!friend.getUser_id_1().getImage().isEmpty()) {
            Picasso.with(context).load(Retro.IMG_URL + friend.getUser_id_1().getImage()).into(holder.profilepic);
        }
        holder.username.setText(friend.getUser_id_1().getName());
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
