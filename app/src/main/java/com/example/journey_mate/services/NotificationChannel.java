package com.example.journey_mate.services;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
public class NotificationChannel {
    Context context;
    public static final String Channel_One="Channel 1";
    public static final String Channel_Two="Channel 2";

    public NotificationChannel(Context context) {
        this.context = context;
    }

    public void NotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            android.app.NotificationChannel channel1 = new android.app.NotificationChannel(Channel_One,"Channel 1", NotificationManager.IMPORTANCE_HIGH);
            android.app.NotificationChannel channel2 = new android.app.NotificationChannel(Channel_Two,"Channel 2", NotificationManager.IMPORTANCE_LOW);
            channel2.setDescription("This is Channel 2");

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }

    }
}



