package com.example.user.recognito.Activities.DashBoardActivityPack;

import android.content.Context;
import android.os.AsyncTask;

import com.example.user.recognito.DataBase.RecognitoDb;
import com.example.user.recognito.DataModels.DataBaseSongModel;

import java.util.List;

/**
 * Created by emmanuel on 3/5/2018.
 */

public class Presenter implements DashBoardContract.Presenter{
    private DashBoardContract.View view;
    private Context context;

    public Presenter(DashBoardContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getSongFromDb(){
        //load data using AsyncTask
        SongAsyncTask songAsyncTask = new SongAsyncTask();
        songAsyncTask.execute();
    }

    public class SongAsyncTask extends AsyncTask<Void, Void, List<DataBaseSongModel>>{

        @Override
        protected List<DataBaseSongModel> doInBackground(Void... voids) {
            final RecognitoDb recognitoDb = new RecognitoDb(context);
            return recognitoDb.getSongsFromDb();
        }

        @Override
        protected void onPostExecute(List<DataBaseSongModel> dataBaseSongModelList) {
            super.onPostExecute(dataBaseSongModelList);
            view.fetchedSongs(dataBaseSongModelList);
        }
    }
}
