package com.example.journey_mate.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.journey_mate.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdaptor extends RecyclerView.Adapter<PostAdaptor.PostHolder>{

    Context context;


    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postdesign, parent,false);
        PostHolder postHolder = new PostHolder(view);
        return postHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class PostHolder extends RecyclerView.ViewHolder{
        CircleImageView profilepic;
        ImageView postimage;
        TextView postcaption,profilename;



        public PostHolder(@NonNull View itemView) {
            super(itemView);
            profilename=itemView.findViewById(R.id.profile_name);
            postcaption = itemView.findViewById(R.id.post_caption);
            postimage = itemView.findViewById(R.id.post_image);
            profilepic= itemView.findViewById(R.id.profile_image);
        }
    }
}
