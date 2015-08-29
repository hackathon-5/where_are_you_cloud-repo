package com.example.miguele.superkids.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by miguele on 8/28/15.
 */
public class SyncInfo {

    private static final String TAG = "syncInfo";

    private static final String STORE_TOTAL_USAGE = "com.example.miguele.superkids.storage.total_usage";
    private static final String STORE_TIME_COUNT = "com.example.miguele.superkids.storage.time_count";
    private static final String STORE_USER_TYPE = "com.example.miguele.superkids.storage.user_type";
    private static final String STORE_SECRET = "com.example.miguele.superkids.storage.secret";

    private static final String TOTAL_USAGE = "total_usage";
    private static final String TIME_COUNT = "time_count";
    private static final String USER_TYPE = "user_type";
    private static final String SECRET = "secret";

    private SyncInfo() {}

    public static void setUserType(Context context, String userType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_USER_TYPE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_TYPE, userType);
        editor.commit();
    }

    public static String getUserType(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_USER_TYPE,
                Context.MODE_PRIVATE);
        String userType = sharedPreferences.getString(USER_TYPE, "");

        return userType;
    }

    public static void setSecret(Context context, String secret) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_SECRET,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SECRET, secret);
        editor.commit();
    }

    public static String getSecret(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_SECRET,
                Context.MODE_PRIVATE);
        String secret = sharedPreferences.getString(SECRET, "");

        return secret;
    }

    public static void setTotalUsage(Context context, long secret) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_TOTAL_USAGE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(TOTAL_USAGE, secret);
        editor.commit();
    }

    public static long getTotalUsage(Context context) {
        Log.i(TAG,"Hello World");
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_TOTAL_USAGE,
                Context.MODE_PRIVATE);
        long secret = sharedPreferences.getLong(TOTAL_USAGE, -1);

        return secret;
    }

    public static void setTimeCount(Context context, int secret) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_TIME_COUNT,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TIME_COUNT, secret);
        editor.commit();
    }

    public static int getTimeCount(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_TIME_COUNT,
                Context.MODE_PRIVATE);
        int secret = sharedPreferences.getInt(TIME_COUNT, 0);

        return secret;
    }


}
