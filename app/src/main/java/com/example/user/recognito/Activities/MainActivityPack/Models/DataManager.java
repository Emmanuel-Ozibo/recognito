package com.example.user.recognito.Activities.MainActivityPack.Models;

import com.example.user.recognito.DataModels.SongDetails;

/**
 * @author Created by emmanuel on 12/10/2017.
 */

public interface DataManager{
    void storeInDataBase(SongDetails songDetails);
    void storeTokenInDb(String accessToken);
}
