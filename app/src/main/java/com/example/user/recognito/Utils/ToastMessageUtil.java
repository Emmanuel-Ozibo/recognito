package com.example.user.recognito.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by emmanuel on 12/20/2017.
 */

public class ToastMessageUtil{

    public static void getToastMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
