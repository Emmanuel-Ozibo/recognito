package com.example.user.recognito.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.example.user.recognito.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by user on 2018-04-20.
 */

public class AboutActivity extends AppCompatActivity{
    private TextView aboutText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutText = findViewById(R.id.about_tv);

//        String text = "";
//        InputStream inputStream;
//        try {
//            inputStream = getResources().getAssets().open("about.html");
//            InputStreamReader reader = new InputStreamReader(inputStream);
//            BufferedReader reader1 = new BufferedReader(reader);
//            text = reader1.readLine();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        aboutText.setText(Html.fromHtml(text));
    }
}
