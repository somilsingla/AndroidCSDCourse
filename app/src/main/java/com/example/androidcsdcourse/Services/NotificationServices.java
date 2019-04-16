package com.example.androidcsdcourse.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import com.example.androidcsdcourse.R;
import com.example.androidcsdcourse.menu;

public class NotificationServices extends Service {
        public NotificationServices() {
        }

        @Override
        public IBinder onBind(Intent intent) {
            // TODO: Return the communication channel to the service.
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
            createNotification("Hey Bro");
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        }

        void createNotification(String message){
            String channel_id = "anything123";

            Intent intent = new Intent(this, menu.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 23, intent, 0 );
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Notification.Builder nb = new Notification.Builder(this)
                    .setContentTitle("Android CSD").setContentText(message)
                    .setSmallIcon(R.drawable.twitter).setContentIntent(pendingIntent)
                    .setSound(sound).addAction(R.drawable.twitter, "View", pendingIntent);

            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

            assert notificationManager!=null;

            NotificationChannel channel;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                channel = new NotificationChannel(channel_id, "MyChannel", NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
                nb.setChannelId(channel_id);
            }

            Notification n = nb.build();
            notificationManager.notify(2, n);
        }
    }

