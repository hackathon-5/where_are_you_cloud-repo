package com.example.miguele.superkids.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.miguele.superkids.R;
import com.example.miguele.superkids.storage.SyncInfo;
import com.txusballesteros.bubbles.BubbleLayout;
import com.txusballesteros.bubbles.BubblesManager;
import com.txusballesteros.bubbles.OnInitializedCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Usage extends Activity {
    @Bind(R.id.usage_button)
    Button usageBtn;

    private BubblesManager bubblesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usage_layout);

        ButterKnife.bind(this);
//        getUserPermission();
        initializeBubblesManager();

        usageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UStats.printCurrentUsageStatus(Usage.this);
//                addNewBubble();
                SyncInfo.setTimeCount(v.getContext(), 0);
                startService(new Intent(Usage.this, PollService.class));
            }
        });
    }

    private void addNewBubble() {
        BubbleLayout bubbleView = (BubbleLayout) LayoutInflater.from(Usage.this).inflate(R.layout.bubble_layout, null);
        bubbleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bubbleView.setOnBubbleRemoveListener(new BubbleLayout.OnBubbleRemoveListener() {
            @Override
            public void onBubbleRemoved(BubbleLayout bubble) {

            }
        });
        bubblesManager.addBubble(bubbleView, 60, 20);
    }

    private void initializeBubblesManager() {
        bubblesManager = new BubblesManager.Builder(this)
                .setTrashLayout(R.layout.bubble_trash_layout)
                .setInitializationCallback(new OnInitializedCallback() {
                    @Override
                    public void onInitialized() {
                        // hook up listener for time limit exceeded
                    }
                })
                .build();
        bubblesManager.initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bubblesManager.recycle();
    }

//    private void getUserPermission() { //Check if permission enabled for usage stats
//        if (UStats.getUsageStatsList(this).isEmpty()) {
//            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
//            startActivity(intent);
//        }
//    }
}