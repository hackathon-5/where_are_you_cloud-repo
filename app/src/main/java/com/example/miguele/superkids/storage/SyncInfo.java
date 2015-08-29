package com.example.miguele.superkids.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by miguele on 8/28/15.
 */
public class SyncInfo {

    private static final String STORE_TOTAL_USAGE = "com.example.miguele.superkids.storage.total_usage";
    private static final String STORE_USER_TYPE = "com.example.miguele.superkids.storage.user_type";
    private static final String STORE_SECRET = "com.example.miguele.superkids.storage.secret";

    private static final String TOTAL_USAGE = "total_usage";
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

    public static void setTotalUsage(Context context, String secret) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_TOTAL_USAGE,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOTAL_USAGE, secret);
        editor.commit();
    }

    public static String getTotalUsage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                STORE_TOTAL_USAGE,
                Context.MODE_PRIVATE);
        String secret = sharedPreferences.getString(TOTAL_USAGE, "");

        return secret;
    }
}
