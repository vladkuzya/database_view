package com.example.alarstudios.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Util for saving and loading value into SharedPreferences by unique keys
 *
 * @author Vladislav Kuzmin
 * @since 1.0
 */
public class UtilSharedPreferences {
    public static void saveValue(Activity activity, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String loadValue(Activity activity, String key, String defValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        return sharedPreferences.getString(key, defValue);
    }
}
