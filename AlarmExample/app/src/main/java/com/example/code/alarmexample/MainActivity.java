package com.example.code.alarmexample;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void showNotificationWithAlarmOnClick(View view) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 5 * 1000, 5 * 1000, pendingIntent);
    }


    public void showNotificationOnClick(View view) {
        String id = "my_channel_01";
        // The user-visible name of the channel.
        CharSequence name = getString(R.string.channel_name);
        // The user-visible description of the channel.
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
        // Configure the notification channel.
        mChannel.setDescription(description);
        mChannel.enableLights(true);
        // Sets the notification light color for notifications posted to this
        // channel, if the device supports this feature.
        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationManager.createNotificationChannel(mChannel);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, id)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
        notificationManager.notify(10, mBuilder.build());
    }

    public void ukloniNotifikaciju(View view) {
        notificationManager.cancel(10);
    }
}
