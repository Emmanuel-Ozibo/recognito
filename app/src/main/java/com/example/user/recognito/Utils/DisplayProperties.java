package com.example.user.recognito.Utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by emmanuel on 1/2/2018.
 */

public class DisplayProperties{

    public static int getScreenHeight(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null){
            Display display =  windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            return point.y;
        }
        return 0;
    }

    public static int getScreenWidth(Context context){
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null){
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            return point.x;
        }
        return 0;
    }
}
