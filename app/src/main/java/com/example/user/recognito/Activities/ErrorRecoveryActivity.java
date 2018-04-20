package com.example.user.recognito.Activities;

import android.content.Intent;
import com.example.user.recognito.Utils.Constant;
import com.example.user.recognito.Utils.ToastMessageUtil;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;


/**
 * Created by emmanuel on 12/26/2017.
 */

public abstract class ErrorRecoveryActivity extends YouTubeBaseActivity implements
    YouTubePlayer.OnInitializedListener{

    public static final int REQUEST_ERROR = 1;

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_ERROR);
        }else {
            String errMessage = youTubeInitializationResult.toString();
            ToastMessageUtil.getToastMessage(this, errMessage);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ERROR){
            getYouTubePlayerProvider().initialize(Constant.YOUTUBE_API_KEY, this);
        }
    }

    public abstract YouTubePlayer.Provider getYouTubePlayerProvider();
}
