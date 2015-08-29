package com.example.miguele.superkids.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.miguele.superkids.R;
import com.example.miguele.superkids.storage.SyncInfo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeActivity extends Activity {

    @Bind(R.id.parent_btn) Button mParentBtn;
    @Bind(R.id.child_btn) Button mChildBtn;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    @OnClick(R.id.parent_btn)
    public void parentPress(View v) {
        SyncInfo.setUserType(mContext, "parent");
        Intent challengerIntent = new Intent(HomeActivity.this, RegistrationActivity.class);
        HomeActivity.this.startActivity(challengerIntent);
    }

    @OnClick(R.id.child_btn)
    public void childPress(View v) {
        SyncInfo.setUserType(mContext, "child");
        Intent challengerIntent = new Intent(HomeActivity.this, RegistrationActivity.class);
        HomeActivity.this.startActivity(challengerIntent);
    }

}
