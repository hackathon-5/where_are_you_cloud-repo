package com.example.miguele.superkids.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by miguele on 8/28/15.
 */
public class SyncInfo {

    private static final String STORE_USER_TYPE = "com.example.miguele.superkids.storage.user_type";

    private static final String USER_TYPE = "user_type";

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
}
