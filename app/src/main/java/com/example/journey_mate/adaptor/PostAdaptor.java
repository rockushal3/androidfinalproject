package com.example.journey_mate.adaptor;

import android.content.Context;
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
import com.example.journey_mate.model.PostResponce;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.postimage.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fadein));
        holder.postbox.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fadepost));
        final PostResponce post = listpost.get(position);
        holder.postcaption.setText(post.getCaption());
        holder.profilename.setText(post.getUser_id().getName());
        if(!post.getImage().isEmpty()) {
            Picasso.with(context).load(Retro.POST_IMG_URL + post.getImage()).into(holder.postimage);
        }
//        if(!post.getUser_id().getImage().isEmpty()){
            Picasso.with(context).load(Retro.IMG_URL + post.getUser_id().getImage()).into(holder.profilepic);
//        }

        System.out.println(post.getUser_id().getImage());

    }

    @Override
    public int getItemCount() {
        return listpost.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder{
        CircleImageView profilepic;
        ImageView postimage;
        TextView postcaption,profilename;
        RelativeLayout postbox;




        public PostHolder(@NonNull View itemView) {
            super(itemView);
            profilename=itemView.findViewById(R.id.profile_name);
            postcaption = itemView.findViewById(R.id.post_caption);
            postimage = itemView.findViewById(R.id.post_image);
            profilepic= itemView.findViewById(R.id.profile_image);
            postbox = itemView.findViewById(R.id.post_box);
        }
    }
}
