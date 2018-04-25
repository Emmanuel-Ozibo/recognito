package com.example.user.recognito.Utils;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by emmanuel on 2018-04-24.
 */

public class CustomExecutor implements Executor{

    public static CustomExecutor newInstance() {
       return new CustomExecutor();
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        new Thread(runnable).start();
    }
}
