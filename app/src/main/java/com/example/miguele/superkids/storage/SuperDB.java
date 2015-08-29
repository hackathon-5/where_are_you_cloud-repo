package com.example.miguele.superkids.storage;

import android.content.Context;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.math.BigDecimal;

/**
 * Created by miguele on 8/28/15.
 */
public class SuperDB {

    private static long timeUsage;

    private SuperDB() {
    }

    public static void addGroup(Context context) {

    }

    public static int getTimeUsage(final Context context) {
        String secret = SyncInfo.getSecret(context);
        Firebase.setAndroidContext(context);
        Firebase kidsFirebase = new Firebase("https://superkids.firebaseio.com/");

        Firebase timeRef = kidsFirebase.child("data").child(secret).child("awards").child("usage");

//        final int timeUsage = 0;
//        final String response;
//        final long timeUsage = 0;
        timeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                timeUsage = (long) dataSnapshot.getValue();
                Toast.makeText(context, Long.toString(timeUsage), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return new BigDecimal(timeUsage).intValue();

    }

    public static void setTimeUsage(final Context context, int timeMins) {
        String secret = SyncInfo.getSecret(context);
        Firebase.setAndroidContext(context);
        Firebase kidsFirebase = new Firebase("https://superkids.firebaseio.com/");

        Firebase timeRef = kidsFirebase.child("data").child(secret).child("awards").child("usage");
        timeRef.setValue(timeMins);

    }


}
