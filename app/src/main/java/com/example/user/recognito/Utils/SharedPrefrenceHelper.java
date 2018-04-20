package com.example.user.recognito.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 12/23/2017.
 */

public class SharedPrefrenceHelper {
    private static final String NAME = "main_file";

    public static boolean alreadyStarted(Context context){
        boolean containsBool = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        boolean isInside = sharedPreferences.getBoolean("has_started", false);
        if (isInside){
            containsBool = true;
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("has_started", true);
            editor.apply();//writes it in the background
        }
        return containsBool;
    }
}
