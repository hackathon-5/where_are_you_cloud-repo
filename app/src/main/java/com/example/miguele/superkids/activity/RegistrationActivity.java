package com.example.miguele.superkids.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miguele.superkids.R;
import com.melnykov.fab.FloatingActionButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends Activity {

//    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.secret_edit_txt) EditText secretEditTxt;
    @Bind(R.id.registration_fab) FloatingActionButton mRegistrationFab;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        mContext = this;
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

    @OnClick(R.id.registration_fab)
    public void registrationSubmit(View v) {
        Toast.makeText(mContext, "Hello there", Toast.LENGTH_SHORT).show();

        // Verify secret is valid

        // Add secret to database
    }

    private void verifySecret(String secret) {

    }

    private void addSecret(String secret) {

    }


}
