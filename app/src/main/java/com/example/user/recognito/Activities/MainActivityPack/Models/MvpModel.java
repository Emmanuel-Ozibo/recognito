package com.example.user.recognito.Activities.MainActivityPack.Models;


import com.example.user.recognito.DataModels.SongDetails;
import com.example.user.recognito.Activities.MainActivityPack.RecognitoMainContract.Contracts;

/**
 * Created by emmanuel on 12/10/2017.
 */

public class MvpModel implements DataManager{
    private Contracts.Presenter presenter;

    public MvpModel(Contracts.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void storeInDataBase(SongDetails songDetails) {

    }

    @Override
    public void storeTokenInDb(String accessToken) {

    }
}
