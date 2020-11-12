package com.example.primeraclase.utils;

import android.content.SharedPreferences;

import com.example.primeraclase.Context;

public class SharedPreferencesUtils {
    private final static String SHARED_KEY = "Blockbuster_app";
    private static SharedPreferences sharedPreferences;

    public static String getKeyValueString(String key){
        String value;

        initShareIfNot();

        value = sharedPreferences.getString(key,null);
        return value;
    }

    public static boolean setKeyValueString(String key, String value){
        initShareIfNot();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        if(editor.commit())
            return true;
        else
            return false;
    }

    private static void initShareIfNot(){
        if(sharedPreferences == null)
            sharedPreferences = Context.getContext().
                    getSharedPreferences(SHARED_KEY,Context.getContext().MODE_PRIVATE);
    }
}
