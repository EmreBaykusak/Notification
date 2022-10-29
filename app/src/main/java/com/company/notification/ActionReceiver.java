package com.company.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String text = intent.getStringExtra("toast");
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
