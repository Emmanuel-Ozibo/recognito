package com.example.user.recognito.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by emmanuel on 12/23/2017.
 */


public class SharedPrefrenceHelper {
    private static final String NAME = "main_file";
    private static final String TOKEN_KEY = "token";

    private static SharedPreferences getPreference(Context context){
        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static void saveAccessToken(Context context, String value){
        SharedPreferences sharedPreferences = getPreference(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(value, "");
        editor.apply();
    }
}
