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
import com.example.journey_mate.model.PostResponce;
import com.example.journey_mate.model.Trip;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class TripAdaptor extends RecyclerView.Adapter<TripAdaptor.TripHolder> {

    Context context;
    List<Trip> listtrip;

    public TripAdaptor(Context context, List<Trip> listtrip) {
        this.context = context;
        this.listtrip = listtrip;
    }

    @NonNull
    @Override
    public TripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.triptimelineview, parent, false);
        TripHolder postHolder = new TripHolder(view);
        return postHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TripHolder holder, int position) {
        final Trip post = listtrip.get(position);
        holder.tripdate.setText(post.getDate());
        holder.tripname.setText(post.getTrip_name());
        holder.tripdesc.setText(post.getDescription());

    }

    @Override
    public int getItemCount() {
        return listtrip.size();
    }


    public class TripHolder extends RecyclerView.ViewHolder {
        TextView tripname,tripdate,tripdesc;

        public TripHolder(@NonNull View itemView) {
            super(itemView);
            tripname= itemView.findViewById(R.id.tripname);
            tripdate = itemView.findViewById(R.id.date);
            tripdesc = itemView.findViewById(R.id.description);
        }
    }


}
