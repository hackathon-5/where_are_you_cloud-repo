package com.example.miguele.superkids.storage;

import android.content.Context;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by miguele on 8/28/15.
 */
public class SuperDB {

    private SuperDB() {
    }

    public static void addGroup(Context context) {

    }

    public static int getTimeUsage(final Context context) {
        String secret = SyncInfo.getSecret(context);
        Firebase.setAndroidContext(context);
        Firebase kidsFirebase = new Firebase("https://superkids.firebaseio.com/");


        Firebase timeRef = kidsFirebase.child("data").child(secret).child("awards").child("usage");

//        final int timeUsage = 0;
//        final String response;
        timeRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String response = (String) dataSnapshot.getValue();
                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return 0;
    }
}



