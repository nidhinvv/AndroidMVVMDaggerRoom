package com.dkv.preference;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class PreferenceManager {

    private Context context;
    private static final String APP_PREFERENCE = "app_preference";

    @Inject
    public PreferenceManager(Context context) {

        this.context = context;
    }


    public void setSharedPreferences(String key, String value) {

        SharedPreferences userDetails = context.getSharedPreferences(APP_PREFERENCE,
                                                                     Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userDetails.edit();
        editor.putString(key, value);
        editor.apply();

    }

    public void setSharedPreferences(String key, boolean value) {

        SharedPreferences userDetails = context.getSharedPreferences(APP_PREFERENCE,
                                                                     Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userDetails.edit();
        editor.putBoolean(key, value);
        editor.apply();

    }


    public String getSharedPreference(String key) {

        SharedPreferences sharedPref = context.getSharedPreferences(APP_PREFERENCE,
                                                                    Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");

    }

    public boolean getBooleanSharedPreference(String key) {

        SharedPreferences sharedPref = context.getSharedPreferences(APP_PREFERENCE,
                                                                    Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key,false);

    }

    public void clearSharedPreference() {

        SharedPreferences sharedPref = context.getSharedPreferences(APP_PREFERENCE,
                                                                    Context.MODE_PRIVATE);
        sharedPref.edit().clear().apply();
    }

}
