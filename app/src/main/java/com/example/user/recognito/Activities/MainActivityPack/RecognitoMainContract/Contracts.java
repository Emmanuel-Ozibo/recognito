package com.example.user.recognito.Activities.MainActivityPack.RecognitoMainContract;

import android.content.Context;

import com.example.user.recognito.DataModels.RecognisedSong;
import com.example.user.recognito.DataModels.RequestResult;
import com.example.user.recognito.DataModels.SongDetails;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.TrackModel;
import com.wrapper.spotify.models.Track;

import kaaes.spotify.webapi.android.SpotifyError;
import kaaes.spotify.webapi.android.models.Album;
import retrofit.RetrofitError;

/**
 * Created by emmanuel on 12/10/2017.
 */

public interface Contracts{
    interface View{
        void startRecognition();
        void parsedJavaObject(SongDetails songDetails, String artistName, String title);
        void unsuccessfulJson(String jsonResult);
        void songObjectCreated(RecognisedSong recognisedSong);
        void spotifyRequestPassed(Track track, String youTubeId);
    }

    interface Presenter{
        void startRecognition();
        void checkResult(String jsonResult);
        void createSongObj(SongDetails songDetails);
        void getSuccessVibration(Context context);
        void getFailureVibration(Context context);
        void makeSpotifyRequest(RecognisedSong recognisedSong);
    }
}
