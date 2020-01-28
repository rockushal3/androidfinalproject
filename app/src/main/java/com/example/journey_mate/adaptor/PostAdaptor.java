package com.example.journey_mate.adaptor;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.journey_mate.R;
import com.example.journey_mate.api.Retro;
import com.example.journey_mate.api.UserApi;
import com.example.journey_mate.controller.UserProfile;
import com.example.journey_mate.controller.profile;
import com.example.journey_mate.model.PostResponce;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdaptor extends RecyclerView.Adapter<PostAdaptor.PostHolder>{

    Context context;
    List<PostResponce> listpost;

    public PostAdaptor(Context context,List<PostResponce> listpost) {
        this.context = context;
        this.listpost=listpost;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postdesign, parent,false);
        PostHolder postHolder = new PostHolder(view);
        return postHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PostHolder holder, int position) {
        holder.postimage.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fadein));
        holder.postbox.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fadepost));
        final PostResponce post = listpost.get(position);
        holder.postcaption.setText(post.getCaption());
        holder.profilename.setText(post.getUser_id().getName());
        holder.posttime.setText(timeAgoInWords(post.getDate()));
        if(!post.getImage().isEmpty()) {
            Picasso.with(context).load(Retro.POST_IMG_URL + post.getImage()).into(holder.postimage);
        }
        if(!post.getUser_id().getImage().isEmpty()){
            Picasso.with(context).load(Retro.IMG_URL + post.getUser_id().getImage()).into(holder.profilepic);
        }

        holder.profilename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (post.getUser_id().get_id().equals(UserApi.loginUserDetail.get_id())){

                    Intent intent = new Intent(context, profile.class);
                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View, String>(holder.profilepic, "profileImage");
                    pairs[1] = new Pair<View, String>(holder.profilename, "profileName");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                            pairs);
                    context.startActivity(intent, options.toBundle());

                }
                else {
                    Intent intent = new Intent(context, UserProfile.class);
                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View, String>(holder.profilepic, "profileImage");
                    pairs[1] = new Pair<View, String>(holder.profilename, "profileName");
                    intent.putExtra("Id", post.getUser_id().get_id());
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                            pairs);
                    context.startActivity(intent, options.toBundle());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listpost.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder{
        CircleImageView profilepic;
        ImageView postimage;
        TextView postcaption,profilename,posttime;
        RelativeLayout postbox;





        public PostHolder(@NonNull View itemView) {
            super(itemView);
            profilename=itemView.findViewById(R.id.profile_name);
            postcaption = itemView.findViewById(R.id.post_caption);
            postimage = itemView.findViewById(R.id.post_image);
            profilepic= itemView.findViewById(R.id.post_profileimg);
            postbox = itemView.findViewById(R.id.post_box);
            posttime=itemView.findViewById(R.id.posttime);
        }
    }


    public static String timeAgoInWords(Date from) {
        Date now = new Date();
        long difference = now.getTime() - from.getTime();
        long distanceInMin = difference / 60000;

        if ( 0 <= distanceInMin && distanceInMin <= 1 ) {
            return "Less than 1 min ago";
        } else if ( 1 <= distanceInMin && distanceInMin <= 45 ) {
            return distanceInMin + " min ago";
        } else if ( 45 <= distanceInMin && distanceInMin <= 89 ) {
            return "1 hour";
        } else if ( 90 <= distanceInMin && distanceInMin <= 1439 ) {
            return (distanceInMin / 60) + " hr ago";
        } else if ( 1440 <= distanceInMin && distanceInMin <= 2529 ) {
            return "1 day";
        } else if ( 2530 <= distanceInMin && distanceInMin <= 43199 ) {
            return (distanceInMin / 1440) + "days ago";
        } else if ( 43200 <= distanceInMin && distanceInMin <= 86399 ) {
            return "About 1 month ago";
        } else if ( 86400 <= distanceInMin && distanceInMin <= 525599 ) {
            return  (distanceInMin / 43200) + " months ago";
        } else {
            long distanceInYears = distanceInMin / 525600;
            return distanceInYears + " years ago";
        }
    }
}
