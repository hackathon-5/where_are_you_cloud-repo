package com.example.miguele.superkids.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.miguele.superkids.R;
import com.example.miguele.superkids.storage.SyncInfo;
import com.txusballesteros.bubbles.BubbleLayout;
import com.txusballesteros.bubbles.BubblesManager;
import com.txusballesteros.bubbles.OnInitializedCallback;

public class Alarm extends BroadcastReceiver {
    private static final String TAG = "receiver_alarm";

    private BubblesManager bubblesManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

//        SuperDB.getTimeUsage(context);
        Toast.makeText(context, "sum: " + UStats.printCurrentUsageStatus(context, pm), Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "sum: " + Long.toString(SyncInfo.getTotalUsage(context)), Toast.LENGTH_SHORT).show();

        if (UStats.printCurrentUsageStatus(context, pm) > SyncInfo.getTotalUsage(context)) {
            CancelAlarm(context);

            wl.release();

            Intent myIntent = new Intent(context, BubbleControl.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent);
        }
    }

    private void addNewBubble(Context context) {
        Log.i(TAG,"initializingBubbleManger 3");
        BubbleLayout bubbleView = (BubbleLayout) LayoutInflater.from(context).inflate(R.layout.bubble_layout, null);
        bubbleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Log.i(TAG, "initializingBubbleManger 4");
        bubbleView.setOnBubbleRemoveListener(new BubbleLayout.OnBubbleRemoveListener() {
            @Override
            public void onBubbleRemoved(BubbleLayout bubble) {

            }
        });

        Log.i(TAG, "initializingBubbleManger 5");
        bubblesManager.addBubble(bubbleView, 60, 20);
        Log.i(TAG, "initializingBubbleManger 6");
    }

    private void initializeBubblesManager(Context context) {
        Log.i(TAG,"initializingBubbleManger");
        bubblesManager = new BubblesManager.Builder(context)
                .setTrashLayout(R.layout.bubble_trash_layout)
                .setInitializationCallback(new OnInitializedCallback() {
                    @Override
                    public void onInitialized() {
                        // hook up listener for time limit exceeded
                    }
                })
                .build();
        Log.i(TAG,"initializingBubbleManger 2");
        bubblesManager.initialize();
    }

    public void SetAlarm(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 15, pi);
                //1000 * 60 * 1, pi); // Millisec * Second * Minute
    }

    public void CancelAlarm(Context context) {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}