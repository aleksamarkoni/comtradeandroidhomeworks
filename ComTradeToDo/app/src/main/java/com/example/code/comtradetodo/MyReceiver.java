package com.example.code.comtradetodo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import com.example.code.comtradetodo.Utils.ParcelableUtil;

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = MyReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Upalio se alarm i to je to");
        byte[] todoByte = intent.getByteArrayExtra("todo");
        Todo todo = ParcelableUtil.unmarshall(todoByte, Todo.CREATOR);
        showNotificationOnClick(context, todo);
    }

    private void showNotificationOnClick(Context context, Todo todo) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        String id = "my_channel_01";
        // The user-visible name of the channel.
        CharSequence name = context.getString(R.string.channel_name);
        // The user-visible description of the channel.
        String description = context.getString(R.string.channel_description);
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
                new NotificationCompat.Builder(context, "my_channel_01")
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle(todo.getTitle())
                        .setContentText("Move your lazy ass");
        notificationManager.notify(10, mBuilder.build());
    }
}
