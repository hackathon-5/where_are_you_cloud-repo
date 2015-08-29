package com.example.miguele.superkids.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.miguele.superkids.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends Activity {
    @Bind(R.id.next_button)
    Button nextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Usage.class));
            }
        });

    }
}