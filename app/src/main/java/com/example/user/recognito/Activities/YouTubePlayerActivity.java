package com.example.user.recognito.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.user.recognito.R;
import com.example.user.recognito.Utils.Constant;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;




/**
 * Created by emmanuel on 12/26/2017.
 */

public class YouTubePlayerActivity extends ErrorRecoveryActivity{
    private YouTubePlayerFragment youTubePlayerFragment;private FragmentManager fragmentManager;
    private String videoId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        Intent intent = getIntent();
        videoId = intent.getStringExtra("vid");

        fragmentManager = getFragmentManager();
        youTubePlayerFragment = YouTubePlayerFragment.newInstance();
        addFragment(youTubePlayerFragment);

        youTubePlayerFragment.initialize(Constant.YOUTUBE_API_KEY, this);

    }

    @Override
    public YouTubePlayer.Provider getYouTubePlayerProvider() {
        return YouTubePlayerFragment.newInstance();
    }

    private void addFragment(Fragment fragment){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.youTubeFragmentContainer, fragment);
        ft.commit();
    }

    private void removeFragment(Fragment fragment){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b){
            youTubePlayer.cueVideo(videoId);
        }

    }
}