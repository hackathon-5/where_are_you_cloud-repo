package com.example.miguele.superkids.activity;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class UStats {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy HH:mm:ss");
    public static final String TAG = UStats.class.getSimpleName();

    @SuppressWarnings("ResourceType")
    public static void getStats(Context context) {
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService("usagestats");
        int interval = UsageStatsManager.INTERVAL_YEARLY;
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.YEAR, -1);
        long startTime = calendar.getTimeInMillis();

        Log.d(TAG, "Range start:" + dateFormat.format(startTime));
        Log.d(TAG, "Range end:" + dateFormat.format(endTime));

        UsageEvents uEvents = usm.queryEvents(startTime, endTime);
        while (uEvents.hasNextEvent()) {
            UsageEvents.Event e = new UsageEvents.Event();
            uEvents.getNextEvent(e);

            if (e != null) {
                Log.d(TAG, "Event: " + e.getPackageName() + "\t" + e.getTimeStamp());
            }
        }
    }

    public static List<UsageStats> getUsageStatsList(Context context) {
        UsageStatsManager usm = getUsageStatsManager(context);
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.YEAR, -1);
        long startTime = calendar.getTimeInMillis();

        Log.d(TAG, "Range start:" + dateFormat.format(startTime));
        Log.d(TAG, "Range end:" + dateFormat.format(endTime));

        List<UsageStats> usageStatsList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);
        return usageStatsList;
    }

    public static long getUsageStats(List<UsageStats> usageStatsList) {
        long sum = 0;

        for (UsageStats u : usageStatsList) {
            long time = u.getTotalTimeInForeground();

            if (!u.getPackageName().contains("com.android")) {
                sum += time;
            }

            int sec  = (int)(time/ 1000) % 60 ;
            int min  = (int)((time/ (1000*60)) % 60);
            int hr   = (int)((time/ (1000*60*60)) % 24);

//            Log.d(TAG, "Pkg: " + u.getPackageName() + "\t" + "ForegroundTime: "
//                    + hr + "h " + min + "m " + sec + "s");
        }

        //Log.d(TAG, "sum: " + sum);

        return sum;
    }


    // set up polling usage against set time value
    // get time value when it is adjusted by the parent

    public static void toastUsageStats(List<UsageStats> usageStatsList, Context context) {

        for (UsageStats u : usageStatsList) {
            long time = u.getTotalTimeInForeground();
            int sec  = (int)(time/ 1000) % 60 ;
            int min  = (int)((time/ (1000*60)) % 60);
            int hr   = (int)((time/ (1000*60*60)) % 24);

            Toast.makeText(context, "Pkg: " + u.getPackageName() + "\t" + "ForegroundTime: "
                    + hr + "h " + min + "m " + sec + "s", Toast.LENGTH_SHORT).show();
        }
    }

    public static long printCurrentUsageStatus(Context context) {
        return getUsageStats(getUsageStatsList(context));
        //toastUsageStats(getUsageStatsList(context), context);
    }

    @SuppressWarnings("ResourceType")
    private static UsageStatsManager getUsageStatsManager(Context context) {
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService("usagestats");
        return usm;
    }
}