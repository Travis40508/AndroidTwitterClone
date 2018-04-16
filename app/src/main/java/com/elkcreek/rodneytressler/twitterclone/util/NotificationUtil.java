package com.elkcreek.rodneytressler.twitterclone.util;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.accessibility.AccessibilityEvent;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.elkcreek.rodneytressler.twitterclone.ui.PostsView.PostsActivity;

/**
 * Created by rodneytressler on 4/12/18.
 */

public class NotificationUtil {

    public static final int CLICK_NOTIFICATION_ID = 1200;
    public static final int CLICK_PENDING_INTENT_ID = 3417;
    public static final int ACTION_IGNORE_NOTIFICATION_PENDING_INTENT_ID = 3418;
    public static final int ACTION_CONFIRM_NOTIFICATION_PENDING_INTENT_ID = 3419;
    public static final String CLICK_REMINDER_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";

    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, PostsActivity.class);
        startActivityIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        return PendingIntent.getActivity(
                context,
                CLICK_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

    private static Bitmap getLargeIcon(Context context) {
        Resources res = context.getResources();

        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_tweet_notification);
        return largeIcon;
    }

    public static void clearAllNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancelAll();
    }

    public static void notifyTweetReceived(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    CLICK_REMINDER_NOTIFICATION_CHANNEL_ID,
                    "Tweet Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, CLICK_REMINDER_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_tweet_notification)
                .setLargeIcon(getLargeIcon(context))
                .setContentTitle("Tweet Received!")
                .setContentText("A new Tweet has been left, read it?")
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        notificationManager.notify(CLICK_NOTIFICATION_ID, notificationBuilder.build());
    }
}
