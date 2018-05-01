package com.example.user.recognito.Activities.MainActivityPack.Models;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Vibrator;
import com.example.user.recognito.DataModels.Artist;
import com.example.user.recognito.DataModels.RecognisedSong;
import com.example.user.recognito.DataModels.SongDetails;
import com.example.user.recognito.Activities.MainActivityPack.RecognitoMainContract.Contracts;
import com.example.user.recognito.DataModels.YouTube;
import com.example.user.recognito.Utils.Constant;
import com.example.user.recognito.Utils.CustomExecutor;
import com.google.common.util.concurrent.SettableFuture;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.TrackRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;
import com.wrapper.spotify.models.Track;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;



/**
 * Created by emmanuel on 12/10/2017.
 */

public class Presenter implements Contracts.Presenter{
    private static Contracts.View view;
    private MvpModel mvpModel;


    public Presenter(Contracts.View view) {
        this.view = view;
        this.mvpModel = new MvpModel(this);
    }

    @Override
    public void startRecognition() {
        view.startRecognition();
    }


    @Override
    public void checkResult(String jsonResult) {
        /**
         * This will check the json result, if the request was successful or not
         * */

        try {
            JSONObject jsonObject = new JSONObject(jsonResult);
            JSONObject jsonObject1 = jsonObject.getJSONObject("status");
            int code = jsonObject1.getInt("code");
            if (code == 0){
                convertToPojo(jsonResult);
            }else {
                String errMessage = jsonObject1.getString("msg");
                view.unsuccessfulJson(errMessage);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createSongObj(SongDetails songDetails) {
        //get the relevant object here
        String youTubeId = "nCgQDjiotG0";
        // TODO: 3/9/2018 get the youTube id
        //get the spotify id and youtube id
        String albumId = songDetails.getMetadata().getMusic().get(0).getExternal_metadata().getSpotify().album.getId();
        String trackId = songDetails.getMetadata().getMusic().get(0).getExternal_metadata().getSpotify().track.getId();
        List<Artist> artistList = songDetails.getMetadata().getMusic().get(0).getExternal_metadata().getSpotify().artists;
        YouTube youTube = songDetails.getMetadata().getMusic().get(0).getExternal_metadata().getYoutube();
        if (youTube != null){
            youTubeId = youTube.getVid();
        }
        RecognisedSong recognisedSong = new RecognisedSong(albumId, youTubeId, trackId, artistList);
        view.songObjectCreated(recognisedSong);
    }


    @Override
    public void getSuccessVibration(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null)
        vibrator.vibrate(300);
    }

    @Override
    public void getFailureVibration(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {500L,500L};
        if (vibrator != null)
        vibrator.vibrate(pattern, 1);
    }

    @Override
    public void makeSpotifyRequest(final RecognisedSong recognisedSong) {
        PresenterAsynTask asynTask = new PresenterAsynTask(recognisedSong);
        asynTask.execute();
    }

    //create an object for the asynTask
    private static class PresenterAsynTask extends AsyncTask<Void, Void, Track>{
        private RecognisedSong recognisedSong;

        public PresenterAsynTask(RecognisedSong recognisedSong){
            this.recognisedSong = recognisedSong;
        }

        @Override
        protected Track doInBackground(Void... voids){
            Track track = null;

            Api api = Api.builder()
                    .clientId(Constant.CLIENT_ID)
                    .clientSecret(Constant.CLIENT_SECRET)
                    .build();

            ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();

            SettableFuture<ClientCredentials> future = request.getAsync();
            try {
                ClientCredentials clientCredentials = future.get();
                String token = clientCredentials.getAccessToken();
                api.setAccessToken(token);
                TrackRequest trackRequest = api.getTrack(recognisedSong.getTrackId()).build();
                track = trackRequest.get();
            } catch (InterruptedException e){
                e.printStackTrace();

            } catch (ExecutionException e){
                e.printStackTrace();
            } catch (WebApiException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return track;
        }

        @Override
        protected void onPostExecute(Track track) {
            super.onPostExecute(track);
            view.spotifyRequestPassed(track, recognisedSong.getYouTubeId());

        }
    }

    private void convertToPojo(String jsonResult) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<SongDetails> songDetailsJsonAdapter = moshi.adapter(SongDetails.class);
        try {
            SongDetails songDetails = songDetailsJsonAdapter.fromJson(jsonResult);
            if (songDetails != null){
                String songTitle = songDetails.getMetadata().getMusic().get(0).getTitle();
                String songArtist =  songDetails.getMetadata().getMusic().get(0).getArtists().get(0).getName();
                view.parsedJavaObject(songDetails, songTitle, songArtist);
            }else {
                throw new NullPointerException("This bitch is null");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
