package com.example.miguele.superkids.activity;

import android.app.Activity;
import android.app.usage.UsageStats;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.miguele.superkids.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Usage extends Activity {
    @Bind(R.id.usage_button)
    Button usageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usage_layout);
        ButterKnife.bind(this);

        //Check if permission enabled
        if (UStats.getUsageStatsList(this).isEmpty()) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }

        usageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UStats.printCurrentUsageStatus(Usage.this);
            }
        });

    }

}
