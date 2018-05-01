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
    public void insertSongIntoDb(RecognisedSong recognisedSong) {
        recMvpModel.putRecognisedSongIntoDb(recognisedSong);
    }
}
