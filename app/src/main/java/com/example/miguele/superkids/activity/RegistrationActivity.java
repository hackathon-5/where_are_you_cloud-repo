package com.example.miguele.superkids.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miguele.superkids.R;
import com.example.miguele.superkids.storage.SyncInfo;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.melnykov.fab.FloatingActionButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends Activity {

    private static final String TAG = "activity_registration";

//    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.secret_edit_txt) EditText mSecretEditTxt;
    @Bind(R.id.registration_fab) FloatingActionButton mRegistrationFab;

    private Firebase kidsFirebase = null;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.primaryBlue500));
        getWindow().setStatusBarColor(getResources().getColor(R.color.primaryBlue700));

        ButterKnife.bind(this);
        mContext = this;
        setupDB();
        setupUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupUI() {
        //initializeToolbar();
    }

//    private void initializeToolbar() {
//        if (mToolbar != null) {
//            mToolbar.setTitle("Register");
//            mToolbar.setBackgroundColor(getResources().getColor(R.color.primaryColor));
//
//            setActionBar(mToolbar);
//        }
//    }

    private void setupDB() {
        Firebase.setAndroidContext(mContext);
        kidsFirebase = new Firebase("https://superkids.firebaseio.com/");
    }

    @OnClick(R.id.registration_fab)
    public void registrationSubmit(View v) {
        String secret = mSecretEditTxt.getText().toString();

        // Verify secret is valid
        verifySecret(secret);
        // Add secret to database
    }

    private void verifySecret(final String secret) {
        Firebase secretRef = kidsFirebase.child("data").child(secret);

        secretRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {

                    if (SyncInfo.getUserType(mContext) == "child") {
                        // Time to sync...
                        addSecret(secret);
                        // Done thank you! have a view for thank you!
                        Intent challengerIntent = new Intent(RegistrationActivity.this, Usage.class);
                        RegistrationActivity.this.startActivity(challengerIntent);
                        return;
                    } else {
                        Toast.makeText(mContext, "Sorry secret already exist", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // If child and dataSnapshot is nil this fails!
                if ((SyncInfo.getUserType(mContext) == "child")){
                    Toast.makeText(mContext, "Sorry need to set up parent account first", Toast.LENGTH_SHORT).show();
                } else {
                    addSecret(secret);
                    // Onto ControllerSelection
                    Intent challengerIntent = new Intent(RegistrationActivity.this, CategorySelectionActivity.class);
                    RegistrationActivity.this.startActivity(challengerIntent);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    // Parent add secret
    private void addSecret(String secret) {
        Log.i(TAG, "adding secret");

        Firebase secretRef = kidsFirebase.child("data").child(secret);

        // Save secret
        SyncInfo.setSecret(mContext, secret);

        String userType = SyncInfo.getUserType(mContext);
        String value = userType + "-" + secret;

        // Add value based on userType
        secretRef.child(userType).setValue(value);
        return;
    }


}
