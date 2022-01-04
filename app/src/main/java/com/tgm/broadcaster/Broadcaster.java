package com.tgm.broadcaster;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Broadcaster extends BroadcastReceiver {

    private String CHANNEL_ID = "100";
    private int NOTIFICATION_ID = 100;
    private String CHANNEL_NAME = "Alarm";

    @Override
    public void onReceive(Context context, Intent intent) {

//      we will use vibrator first
//        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//        vibrator.vibrate(4000);
//
//        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        if (alarmUri == null) {
//            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        }
//
//        // setting default ringtone
//        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
//
//        // play ringtone
//        ringtone.play();

        registerNotificationChannel(context);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(CHANNEL_NAME)
                .setContentText("Alarm Service")
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true); // Notice this code calls setAutoCancel(), which automatically removes the notification when the user taps it.

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

        Toast.makeText(context, "Alarm On..", Toast.LENGTH_LONG).show();

//        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.airplane_mode), Toast.LENGTH_LONG).show();
//        if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.power_connected), Toast.LENGTH_LONG).show();
//        else if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.power_disconnected), Toast.LENGTH_LONG).show();
//        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.BATTERY_CHANGED), Toast.LENGTH_LONG).show();
//        else if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.BATTERY_LOW), Toast.LENGTH_LONG).show();
//        else if (Intent.ACTION_BATTERY_OKAY.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.BATTERY_OKAY), Toast.LENGTH_LONG).show();
//        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.BOOT_COMPLETED), Toast.LENGTH_LONG).show();
//        if (Intent.ACTION_LOCKED_BOOT_COMPLETED.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.LOCKED_BOOT_COMPLETED), Toast.LENGTH_LONG).show();
//        if (Intent.ACTION_DEVICE_STORAGE_LOW.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.DEVICE_STORAGE_LOW), Toast.LENGTH_LONG).show();
//        if (Intent.ACTION_DEVICE_STORAGE_OK.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.DEVICE_STORAGE_OKAY), Toast.LENGTH_LONG).show();
//        if (Intent.ACTION_SHUTDOWN.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.SHUTDOWN), Toast.LENGTH_LONG).show();
//        if (Intent.ACTION_CAMERA_BUTTON.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.CAMERA_BUTTON), Toast.LENGTH_LONG).show();
//        if (Intent.ACTION_NEW_OUTGOING_CALL.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.NEW_OUTGOING_CALL), Toast.LENGTH_LONG).show();
//        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.SCREEN_OFF), Toast.LENGTH_LONG).show();
//        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.SCREEN_ON), Toast.LENGTH_LONG).show();
//        if (Intent.ACTION_WALLPAPER_CHANGED.equals(intent.getAction()))
//            Toast.makeText(context, context.getString(R.string.power_connected), Toast.LENGTH_LONG).show();
    }

    private void registerNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
