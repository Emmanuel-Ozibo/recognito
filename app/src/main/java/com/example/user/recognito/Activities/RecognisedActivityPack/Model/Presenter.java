package com.example.user.recognito.Activities.RecognisedActivityPack.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.user.recognito.Activities.RecognisedActivityPack.RecognisedContract;
import com.example.user.recognito.DataModels.RecognisedSong;
import com.example.user.recognito.DataModels.SpotifyData.Album;
import com.example.user.recognito.DataModels.SpotifyData.TrackWapper;
import com.example.user.recognito.Rest.ApiInterface;
import com.example.user.recognito.Rest.ApiService;
import com.example.user.recognito.Utils.Constant;
import com.example.user.recognito.Utils.CustomExecutor;
import com.example.user.recognito.Utils.ImageBlurUtil;
import com.example.user.recognito.Utils.ImageLoadingUtil;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.RelatedArtistsRequest;
import com.wrapper.spotify.methods.TopTracksRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.ClientCredentials;
import com.wrapper.spotify.models.Track;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void getSimilarArtist(final List<String> artistIds){

    }

    @Override
    public void getTopTracks(String trackId) {
        RecognitoAsynTask asynTask = new RecognitoAsynTask(trackId);
        asynTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private static class RecognitoAsynTask extends AsyncTask<Void, Void, List<Album>>{
        String trackId;
        List<Album>albumList=null;

        private RecognitoAsynTask(String trackId){
            this.trackId = trackId;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Log.i("logger1", "onPreExecute");
        }

        @Override
        protected List<Album>doInBackground(Void... voids){
            Log.i("logger2", "doInBackground");

            Api api = Api.builder()
                    .clientSecret(Constant.CLIENT_SECRET)
                    .clientId(Constant.CLIENT_ID)
                    .build();


//            RelatedArtistsRequest relatedArtistsRequest = api.getArtistRelatedArtists(stringList.get(0)).build();
//            artistList = relatedArtistsRequest.get();

            String accessToken = getAccessToken(api);
            ApiInterface apiInterface = ApiService.getRetrofit(Constant.BASE_URL_SPOTIFY, accessToken)
                    .create(ApiInterface.class);
            Call<TrackWapper> trackWapper = apiInterface.getTopTracks(trackId, "SE");
            trackWapper.enqueue(new Callback<TrackWapper>() {
                @Override
                public void onResponse(Call<TrackWapper> call, Response<TrackWapper> response) {
                    if (response.isSuccessful()){
                        albumList = response.body().getAlbumList();
                    }
                }

                @Override
                public void onFailure(Call<TrackWapper> call, Throwable t) {

                }
            });
            return albumList;
        }

        @Override
        protected void onPostExecute(List<Album> mAlbumList) {
            super.onPostExecute(mAlbumList);
            Log.i("logger3", "onPostExecute");
            view.topTracks(mAlbumList);
        }
    }

    private static String getAccessToken(Api api) {
        String accessToken = null;
        ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();

        SettableFuture<ClientCredentials> future = request.getAsync();

        ClientCredentials clientCredentials = null;
        try {
            clientCredentials = future.get();
            accessToken = clientCredentials.getAccessToken();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

        @Override
    public void insertSongIntoDb(RecognisedSong recognisedSong) {
        recMvpModel.putRecognisedSongIntoDb(recognisedSong);
    }
}
