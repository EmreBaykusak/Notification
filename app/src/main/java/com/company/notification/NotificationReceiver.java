package com.company.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver
{
    public final String CHANNEL_ID = "1";
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(context,101,
                notificationIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT );

        Intent actionIntent = new Intent(context, ActionReceiver.class);

        actionIntent.putExtra("toast", "This is a Notification Message");

        PendingIntent actionPending = PendingIntent.getBroadcast(context, 0,
                actionIntent,PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT );

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                R.drawable.ic_baseline_circle_notifications_24,
                "Toast Message",
                actionPending
        ).build();

        Intent dismissIntent = new Intent(context,DismissReceiver.class);

        PendingIntent dismissPending = PendingIntent.getBroadcast(context,0,
                dismissIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action dismiss = new NotificationCompat.Action.Builder(
                R.drawable.ic_baseline_circle_notifications_24,
                "Dismiss",
                dismissPending
        ).build();

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"1", NotificationManager.IMPORTANCE_DEFAULT);

        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.uk );
        String text = context.getResources().getString(R.string.big_text);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_add_alert_24)
                .setContentTitle("Title")
                .setContentText(text)
                .setContentIntent(notificationPendingIntent) // Pending for time to notify user
                .setAutoCancel(true)
                .addAction(action) // pending for action
                .addAction(dismiss) // pending for dismiss
                .setColor(Color.RED)
                .setLargeIcon(icon)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text));

        NotificationManagerCompat compat = NotificationManagerCompat.from(context);
        compat.notify(1, builder.build());
    }
}
