package com.wowmirroringrecordingapp.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SharedPreferenceClass {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferenceClass(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void set(String key, String value) {
        editor.putString(key, value).commit();
    }

    public String get(String key) {
        String value = "";
        value = sharedPreferences.getString(key, value);
        if (value == null) {
            value = "";
        }
        return value;
    }

}
