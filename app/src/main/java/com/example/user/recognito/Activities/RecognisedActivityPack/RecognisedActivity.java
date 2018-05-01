package com.example.user.recognito.Activities.RecognisedActivityPack;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.example.user.recognito.Activities.DashBoardActivityPack.DashBoardActivity;
import com.example.user.recognito.Activities.MainActivityPack.MainActivity;
import com.example.user.recognito.Activities.YouTubePlayerActivity;
import com.example.user.recognito.DataModels.RecognisedSong;
import com.example.user.recognito.DataModels.YouTubeData.YouTubeResponse;
import com.example.user.recognito.Fragments.RecognisedFragment;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.TrackModel;
import com.example.user.recognito.R;
import com.example.user.recognito.Rest.ApiInterface;
import com.example.user.recognito.Rest.ApiService;
import com.example.user.recognito.Utils.Constant;
import com.example.user.recognito.Utils.ToastMessageUtil;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by emmanuel on 12/25/2017.
 */

public class RecognisedActivity extends AppCompatActivity implements RecognisedFragment.OnRecognisedFragmentListener{
    private FragmentManager fragmentManager;private String youTubeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognised_layout);

        fragmentManager = getSupportFragmentManager();

        //get the incoming intent
        Intent intent = getIntent();
        youTubeId = intent.getStringExtra("youtubeId");
        TrackModel trackModel = intent.getParcelableExtra("trackmodel");

        RecognisedSong recognisedSong = new RecognisedSong(trackModel, youTubeId);
        RecognisedFragment recognisedFragment = RecognisedFragment.newInstance(recognisedSong);
        //add the recognised fragment
        addFragment(recognisedFragment);

    }

    private void addFragment(Fragment fragment){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.frag_container, fragment);
        ft.commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frag_container, fragment);
        ft.commit();
    }

    private void removeFragment(Fragment fragment){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    @Override
    public void onShareButtonClicked(String spotifyUrl){
        String shareText = getResources().getString(R.string.share_text);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share song");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText +":" +"\n" +spotifyUrl);
        startActivity(Intent.createChooser(shareIntent, "Share song via"));
    }

    @Override
    public void onBackButtonClicked(){

        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        onBackButtonClicked();
    }

    @Override
    public void onYouTubeThumbnailViewClicked() {
        if (youTubeId != null && !TextUtils.isEmpty(youTubeId))
        startActivity(new Intent(this, YouTubePlayerActivity.class).putExtra("vid",youTubeId));
    }

    @Override
    public void onDashBoardClicked(){
        startActivity(new Intent(this, DashBoardActivity.class));
    }
}