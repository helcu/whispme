package com.whispcorp.whispme.util;

import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;


public class SharePreferencesUtil {

    public static void setValue(SharedPreferences sp, String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setValue(SharedPreferences sp, HashMap<String, String> hashMap) {
        SharedPreferences.Editor editor = sp.edit();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            editor.putString(entry.getKey(), entry.getValue());
        }
        editor.apply();
    }

    public static String getValue(SharedPreferences sp, String key, String value) {
        return sp.getString(key, value);
    }
}
