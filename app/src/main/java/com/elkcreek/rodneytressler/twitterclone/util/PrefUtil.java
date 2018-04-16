package com.elkcreek.rodneytressler.twitterclone.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

/**
 * Created by rodneytressler on 4/12/18.
 */

public class PrefUtil {
    public static final String KEY_PUSH_NOTIFICATIONS_ENABLED = "push-notifications-enabled";
    private static final boolean DEFAULT_SELECTION = true;

    public static boolean getPushNotificationPreference(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean pushEnabled = prefs.getBoolean(KEY_PUSH_NOTIFICATIONS_ENABLED, DEFAULT_SELECTION);
        return pushEnabled;
    }

    public static void changePushSelection(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_PUSH_NOTIFICATIONS_ENABLED, !getPushNotificationPreference(context));
        editor.apply();
    }
}
