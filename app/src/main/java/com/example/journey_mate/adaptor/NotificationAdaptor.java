package com.example.journey_mate.adaptor;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.journey_mate.R;
import com.example.journey_mate.api.Retro;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.controller.UserProfile;
import com.example.journey_mate.controller.fragment.Option;
import com.example.journey_mate.controller.profile;
import com.example.journey_mate.model.FriendRelationResponce;
import com.example.journey_mate.model.PostResponce;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdaptor extends RecyclerView.Adapter<NotificationAdaptor.NotificationHolder> {

    Context context;
    List<FriendRelationResponce> listpost;

    public NotificationAdaptor(Context context, List<FriendRelationResponce> listpost) {
        this.context = context;
        this.listpost = listpost;
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification, parent, false);
        NotificationHolder postHolder = new NotificationHolder(view);
        return postHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationHolder holder, int position) {
        final FriendRelationResponce post = listpost.get(position);

        if(post.getStatus().equals("Requested")&& post.getUser_id_2().get_id().equals(UserApi.loginUserDetail.get_id())) {
            holder.notification.setText(post.getUser_id_1().getName() +" send you a Friend Request");
            Picasso.with(context).load(Retro.IMG_URL + post.getUser_id_1().getImage()).into(holder.profilepic);

        }

        else if(post.getStatus().equals("Friends")&& post.getUser_id_1().get_id().equals(UserApi.loginUserDetail.get_id())) {
            holder.notification.setText(post.getUser_id_2().getName() +" accept your a Friend Request");
            Picasso.with(context).load(Retro.IMG_URL + post.getUser_id_2().getImage()).into(holder.profilepic);

        }


    }

    @Override
    public int getItemCount() {
        return listpost.size();
    }


    public class NotificationHolder extends RecyclerView.ViewHolder {
        CircleImageView profilepic;
        TextView notification, profilename;
        RelativeLayout postbox;

        public NotificationHolder(@NonNull View itemView) {
            super(itemView);
            notification = itemView.findViewById(R.id.nofication);
            profilepic = itemView.findViewById(R.id.profile_image);
        }
    }

    public static String timeAgoInWords(Date from) {
        Date now = new Date();
        long difference = now.getTime() - from.getTime();
        long distanceInMin = difference / 60000;
        if (0 <= distanceInMin && distanceInMin <= 1) {
            return "Less than 1 min ago";
        } else if (1 <= distanceInMin && distanceInMin <= 45) {
            return distanceInMin + " min ago";
        } else if (45 <= distanceInMin && distanceInMin <= 89) {
            return "1 hour";
        } else if (90 <= distanceInMin && distanceInMin <= 1439) {
            return (distanceInMin / 60) + " hr ago";
        } else if (1440 <= distanceInMin && distanceInMin <= 2529) {
            return "1 day";
        } else if (2530 <= distanceInMin && distanceInMin <= 43199) {
            return (distanceInMin / 1440) + "days ago";
        } else if (43200 <= distanceInMin && distanceInMin <= 86399) {
            return "About 1 month ago";
        } else if (86400 <= distanceInMin && distanceInMin <= 525599) {
            return (distanceInMin / 43200) + " months ago";
        } else {
            long distanceInYears = distanceInMin / 525600;
            return distanceInYears + " years ago";
        }
    }
}
