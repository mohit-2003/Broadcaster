package com.tgm.broadcaster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.tgm.broadcaster.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Broadcaster broadcaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        broadcaster = new Broadcaster();

        // starting a foreground service
        binding.startForegroundService.setOnClickListener(view -> startForegroundService());

        binding.setAlarm.setOnClickListener(view -> {
            setAlarm();
            Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
        });
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
//        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
//        registerReceiver(broadcaster, intentFilter); // for Dynamic Broadcast
    }

    private void setAlarm() {

        Intent intent = new Intent(MainActivity.this, Broadcaster.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            calendar.set(Calendar.HOUR_OF_DAY, binding.timePicker.getHour());
//            calendar.set(Calendar.MINUTE, binding.timePicker.getMinute());
//        }

//        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 60000, pendingIntent);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 10000, 60000, pendingIntent);

    }

    private void startForegroundService() {

        // Foreground services perform operations that are noticeable to the user.
        //
        //Foreground services show a status bar notification, so that users are actively
        // aware that your app is performing a task in the foreground and is consuming system resources.
        // The notification cannot be dismissed unless the service is either stopped or removed from the foreground.

//       Examples of apps that would use foreground services include the following :
//       A music player app that plays music in a foreground service. The notification might show the current song that is being played.
//       A fitness app that records a user's run in a foreground service, after receiving permission from the user.
//       The notification might show the distance that the user has traveled during the current fitness session.

        Intent intent = new Intent(MainActivity.this, MyService.class); // service class
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    @Override
    protected void onDestroy() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                stopService(new Intent(MainActivity.this, MyService.class));
            }
        }, 5000);
        super.onDestroy();
    }
}