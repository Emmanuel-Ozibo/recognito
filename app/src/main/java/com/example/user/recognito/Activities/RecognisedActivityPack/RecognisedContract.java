package com.example.user.recognito.Activities.RecognisedActivityPack;

import android.content.Context;
import android.graphics.Bitmap;
import com.example.user.recognito.DataModels.RecognisedSong;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Track;
import java.util.List;


/**
 * Created by user on 12/30/2017.
 */

public interface RecognisedContract{


    interface RecognisedFragmentView{
        void blurBitmapGenerated(Bitmap blurBitmap);
        void circularBitmapGenerated(Bitmap circularBitmap);
        void topTracks(List<Artist> artistList);
    }

    interface RecognisedFragmentPresenter{
        void getImageBitmaps(Context context, String imagePath);
        void getSimilarArtist(List<String> similarIds);
        void insertSongIntoDb(RecognisedSong recognisedSong);
    }
}
