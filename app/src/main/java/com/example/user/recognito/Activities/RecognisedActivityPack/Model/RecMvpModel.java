package com.example.user.recognito.Activities.RecognisedActivityPack.Model;

import android.content.Context;

import com.example.user.recognito.Activities.RecognisedActivityPack.RecognisedContract;
import com.example.user.recognito.DataBase.RecognitoDb;
import com.example.user.recognito.DataModels.RecognisedSong;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.TrackModel;
import com.example.user.recognito.Utils.ToastMessageUtil;

/**
 * Created by emmanuel on 12/30/2017.
 */

public class RecMvpModel implements DataManager{
    private RecognisedContract.RecognisedFragmentPresenter presenter;
    private Context context;

    public RecMvpModel(Context context, RecognisedContract.RecognisedFragmentPresenter presenter){
        this.presenter = presenter;
        this.context = context;
    }


    @Override
    public void saveSongInDb() {

    }

    @Override
    public void fetchSimilarTracks(TrackModel trackModel) {

    }

    @Override
    public void putRecognisedSongIntoDb(RecognisedSong recognisedSong) {
        RecognitoDb recognitoDb = new RecognitoDb(context);
        long id = recognitoDb.insertRecognisedSongIntoDb(recognisedSong);
        if (id > 0){
            ToastMessageUtil.getToastMessage(context, "Inserted");
        }else {
            ToastMessageUtil.getToastMessage(context, "Not inserted");
        }
    }
}
