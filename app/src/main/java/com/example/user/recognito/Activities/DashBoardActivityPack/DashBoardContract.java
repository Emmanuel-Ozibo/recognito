package com.example.user.recognito.Activities.DashBoardActivityPack;

import com.example.user.recognito.DataModels.DataBaseSongModel;

import java.util.List;

/**
 * Created by emmanuel on 3/5/2018.
 */

public interface DashBoardContract{
    interface View{
        void fetchedSongs(List<DataBaseSongModel> baseSongModelList);
    }
    interface Presenter{
        void getSongFromDb();
    }
}
