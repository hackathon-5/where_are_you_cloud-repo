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

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends Activity {
    @Bind(R.id.next_button)
    Button nextBtn;


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

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Usage.class));
            }
        });

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
}
