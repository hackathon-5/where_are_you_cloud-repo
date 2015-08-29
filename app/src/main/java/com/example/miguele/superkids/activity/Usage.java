package com.example.miguele.superkids.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.miguele.superkids.R;
import com.txusballesteros.bubbles.BubbleLayout;
import com.txusballesteros.bubbles.BubblesManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Usage extends Activity {
    @Bind(R.id.usage_button)
    Button usageBtn;

    private BubblesManager bubblesManager;
    private BubbleLayout bubbleView;

    boolean showBubble = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usage_layout);
        ButterKnife.bind(this);

        bubblesManager = new BubblesManager.Builder(this).build();
        bubblesManager.initialize();

        bubbleView = (BubbleLayout) LayoutInflater
                .from(Usage.this).inflate(R.layout.bubble_layout, null);

        bubblesManager = new BubblesManager.Builder(this)
                .setTrashLayout(R.layout.bubble_trash_layout).build();
        bubblesManager.initialize();

        //Check if permission enabled
        if (UStats.getUsageStatsList(this).isEmpty()) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }

        usageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (showBubble) {
                    UStats.printCurrentUsageStatus(Usage.this);
                    bubblesManager.addBubble(bubbleView, 60, 20);
                    usageBtn.setText("Hide");
                } else {
                    bubblesManager.removeBubble(bubbleView);
                    usageBtn.setText("Show");
                }

                showBubble = !showBubble;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bubblesManager.recycle();
    }
}