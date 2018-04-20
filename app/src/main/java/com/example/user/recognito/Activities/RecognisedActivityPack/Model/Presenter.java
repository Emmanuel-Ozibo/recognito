package com.example.user.recognito.Activities.RecognisedActivityPack.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.example.user.recognito.Activities.RecognisedActivityPack.RecognisedContract;
import com.example.user.recognito.DataModels.RecognisedSong;
import com.example.user.recognito.Utils.Constant;
import com.example.user.recognito.Utils.ImageBlurUtil;
import com.example.user.recognito.Utils.ImageLoadingUtil;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.TopTracksRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;
import com.wrapper.spotify.models.Track;

import java.io.IOException;
import java.util.List;

/**
 * Created by emmanuel on 12/30/2017.
 */

public class Presenter implements RecognisedContract.RecognisedFragmentPresenter{
    private RecMvpModel recMvpModel;
    private static RecognisedContract.RecognisedFragmentView view;

    public Presenter(Context context, RecognisedContract.RecognisedFragmentView view){
        this.recMvpModel = new RecMvpModel(context, this);
        this.view = view;
    }


    /**
     * @param context This is the application context
     * @param imagePath This is the path to the image
     * */
    @Override
    public void getImageBitmaps(final Context context, String imagePath){
        ImageLoadingUtil.loadImage(context, imagePath, new ImageLoadingUtil.BitmapLoadedListener(){
            @Override
            public void onLoad(Bitmap originalBitmap) {
                Bitmap blurBitmap = ImageBlurUtil.blur(context, originalBitmap);
                Bitmap circularBitmap = ImageLoadingUtil.getCircularBitmap(originalBitmap);
                view.blurBitmapGenerated(blurBitmap);
                view.circularBitmapGenerated(circularBitmap);
            }
        });
    }

    @Override
    public void getSimilarArtist(List<String> artistIds) {
        //todo: set similarTracks
        PresenterAsynTask asynTask = new PresenterAsynTask(artistIds);
        asynTask.execute();
    }


    @Override
    public void insertSongIntoDb(RecognisedSong recognisedSong) {
        recMvpModel.putRecognisedSongIntoDb(recognisedSong);
    }

    private static class PresenterAsynTask extends AsyncTask<Void, Void, List<Track>> {
        private List<String> artistIds;

        public PresenterAsynTask(List<String> artistIds) {
            this.artistIds = artistIds;
        }

        //todo: Handle this later
        List<Track> trackList = null;
        @Override
        protected List<Track> doInBackground(Void... voids){
            Api api = Api.builder()
                    .clientId(Constant.CLIENT_ID)
                    .clientSecret(Constant.CLIENT_SECRET)
                    .build();
            ClientCredentialsGrantRequest request = ClientCredentialsGrantRequest.builder().build();
            try {
                ClientCredentials clientCredentials =  request.get();
                String accessToken = clientCredentials.getAccessToken();
                api.setAccessToken(accessToken);

                TopTracksRequest topTracksRequest = api.getTopTracksForArtist(artistIds.get(0), "SE").build();
                trackList = topTracksRequest.get();


            } catch (IOException e){
                e.printStackTrace();
                Log.i("crash", "Crashed");
            } catch (WebApiException e) {
                e.printStackTrace();
                Log.i("crash", "Crashed");
            }

            return trackList;
        }

        @Override
        protected void onPostExecute(List<Track> trackList) {
            super.onPostExecute(trackList);
            view.topTracks(trackList);
        }
    }
}
