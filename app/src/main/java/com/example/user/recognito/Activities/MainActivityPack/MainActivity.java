package com.example.user.recognito.Activities.MainActivityPack;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.acrcloud.rec.sdk.ACRCloudClient;
import com.acrcloud.rec.sdk.ACRCloudConfig;
import com.acrcloud.rec.sdk.IACRCloudListener;
import com.example.user.recognito.Activities.AboutActivity;
import com.example.user.recognito.Activities.DashBoardActivityPack.DashBoardActivity;
import com.example.user.recognito.Activities.RecognisedActivityPack.RecognisedActivity;
import com.example.user.recognito.DataModels.RecognisedSong;
import com.example.user.recognito.DataModels.SpotifyData.ImageModel;
import com.example.user.recognito.Fragments.ErrorFragments.MainActivityErrorFragment;
import com.example.user.recognito.Fragments.FirstFragment;
import com.example.user.recognito.Fragments.PreRecogniseFragment;
import com.example.user.recognito.Fragments.SoundRecognitionAnim;
import com.example.user.recognito.DataModels.SongDetails;
import com.example.user.recognito.Activities.MainActivityPack.Models.Presenter;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.TrackModel;
import com.example.user.recognito.R;
import com.example.user.recognito.Activities.MainActivityPack.RecognitoMainContract.Contracts;
import com.example.user.recognito.Settings.SettingsActivity;
import com.example.user.recognito.Utils.Constant;
import com.wrapper.spotify.models.Image;
import com.wrapper.spotify.models.SimpleArtist;
import com.wrapper.spotify.models.Track;

import android.support.v7.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        IACRCloudListener,
        Contracts.View,
        FirstFragment.OnFirstFragmantAttached,
        SoundRecognitionAnim.OnSoundRecognitionAminAttached,
        MainActivityErrorFragment.OnMainActivityErrorFragmentListener,
        SharedPreferences.OnSharedPreferenceChangeListener{

    private ACRCloudConfig acrCloudConfig;
    private ACRCloudClient acrCloudClient;
    private static final int TIMEOUT = 5000;
    private FragmentManager fragmentManager;
    private Presenter presenter;
    private FirstFragment firstFragment;
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO,Manifest.permission.VIBRATE};
    private static final int REQUEST_PERMISSIONS = 200;
    private boolean vibrationStatus= false;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ask for permission(s) at rum time, but this should happen for SDK 6.0 and above
        if (isAndroidMOrHigher()) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
        }

        //set up sharedPreference
        setUpSharedPreference();

        fragmentManager = getSupportFragmentManager();
        presenter = new Presenter(this);

        firstFragment = new FirstFragment();
        addFragment(firstFragment);

        setUpACRCloud(getConfig());
        acrCloudClient.startPreRecord(3000);
    }

    private void setUpSharedPreference() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        vibrationStatus = preferences.getBoolean(getResources().getString(R.string.vibration_pref), false);
        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    private boolean isAndroidMOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private ACRCloudConfig getConfig(){
        acrCloudConfig = new ACRCloudConfig();
        acrCloudConfig.acrcloudListener = this;
        acrCloudConfig.dbPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "AcrCloud";
        acrCloudConfig.protocol = ACRCloudConfig.ACRCloudNetworkProtocol.PROTOCOL_HTTP;
        acrCloudConfig.requestTimeout = TIMEOUT;
        acrCloudConfig.context = this;
        acrCloudConfig.reqMode = ACRCloudConfig.ACRCloudRecMode.REC_MODE_REMOTE;
        acrCloudConfig.accessKey = Constant.ACCESS_KEY;
        acrCloudConfig.accessSecret = Constant.ACCESS_SECRET;
        acrCloudConfig.host = Constant.LOCAL;
        return acrCloudConfig;
    }

    private void setUpACRCloud(ACRCloudConfig acrCloudConfig){
        acrCloudClient = new ACRCloudClient();
        acrCloudClient.initWithConfig(acrCloudConfig);
    }

    @Override
    public void onResult(String result){
        /**
         * Ensure that This is the right result: and also authenticate it accordingly
         * i will check the message status from here
         * if code == 0: parseJsonResultToJava() will be called
         * else the appropriate fragment will be displayed
         * */
        freeAcrcloudResource();
        presenter.checkResult(result);
    }

    @Override
    public void parsedJavaObject(SongDetails songDetails, String artistName, final String title){
        presenter.createSongObj(songDetails);
        //give a small vibration
        if (vibrationStatus){
            presenter.getSuccessVibration(this);
        }

        PreRecogniseFragment preRecogniseFragment = PreRecogniseFragment.newInstance(artistName, title);
        replaceFragment(preRecogniseFragment);
    }


    @Override
    public void unsuccessfulJson(String jsonResult){
        if (vibrationStatus){
            presenter.getFailureVibration(this);
        }
        MainActivityErrorFragment mainActivityErrorFragment = MainActivityErrorFragment.newInstance(jsonResult);
        replaceFragment(mainActivityErrorFragment);
    }

    @Override
    public void songObjectCreated(RecognisedSong recognisedSong) {
        presenter.makeSpotifyRequest(recognisedSong);
    }

    @Override
    public void spotifyRequestPassed(Track track, String youtubeId){
        TrackModel trackModel = generateTrackModel(track);
        startRecognisedActivity(trackModel, youtubeId);
    }

    private void startRecognisedActivity(TrackModel trackModel, String youtubeId) {
        Intent intent = new Intent(this, RecognisedActivity.class);
        intent.putExtra("trackmodel", trackModel);
        intent.putExtra("youtubeId", youtubeId);
        startActivity(intent);
    }

    private TrackModel generateTrackModel(Track track){
        String trackName = track.getName();
        int duration = track.getDuration();
        int popularity = track.getPopularity();
        List<String> avaliableMarkets = track.getAvailableMarkets();
        List<String> artistNames = getNames(track.getArtists());
        List<ImageModel>imageModelList = getImageModelList(track.getAlbum().getImages());
        String spotifyUrl = track.getExternalUrls().get("spotify");
        return new TrackModel(trackName, duration, popularity,
                imageModelList, avaliableMarkets, artistNames,
                spotifyUrl);
    }

    private List<String> getNames(List<SimpleArtist> artists){
        List<String> names = new ArrayList<>();
        for (SimpleArtist simpleArtist: artists){
            String name = simpleArtist.getName();
            names.add(name);
        }
        return names;

    }

    private List<ImageModel> getImageModelList(List<Image> imageList) {
        List<ImageModel>imageModelList = new ArrayList<>();
        for (Image image:imageList){
            Integer height = image.getHeight();
            String url = image.getUrl();
            Integer width = image.getWidth();
            imageModelList.add(new ImageModel(height, url, width));
        }
        return imageModelList;
    }

    private void freeAcrcloudResource() {
        acrCloudClient.cancel();
    }

    private void freeAcrCloudResourceAndRelease(){
        acrCloudClient.stopPreRecord();
        acrCloudClient.stopRecordToRecognize();
        acrCloudClient.release();
    }

    @Override
    public void onVolumeChanged(double v) {

    }

    @Override
    public void startRecognition() {
        acrCloudClient.startRecognize();
    }


    private void addFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack("add");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

    }

    private void removeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack("replace");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public void onRecognitionButtonClicked() {
        SoundRecognitionAnim soundRecognitionAnim = new SoundRecognitionAnim();
        replaceFragment(soundRecognitionAnim);
        presenter.startRecognition();
    }

    @Override
    public void onDashboardClicked(){
        startActivity(new Intent(this, DashBoardActivity.class));
    }

    @Override
    public void onAboutClicked() {
        startActivity(new Intent(this, AboutActivity.class));
    }

    @Override
    public void onSettingsClicked() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_PERMISSIONS:
                permissionToRecordAccepted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted)finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        preferences.unregisterOnSharedPreferenceChangeListener(this);
        freeAcrCloudResourceAndRelease();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void onCancelClicked() {
        acrCloudClient.cancel();
        replaceFragment(firstFragment);
        setUpACRCloud(getConfig());
        acrCloudClient.startPreRecord(3000);
    }

    @Override
    public void onReTryButtonClicked(){
        onRecognitionButtonClicked();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getResources().getString(R.string.vibration_pref))){
            vibrationStatus = sharedPreferences.getBoolean(getResources().getString(R.string.vibration_pref),  false);
        }
    }
}