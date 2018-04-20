package com.example.user.recognito.Activities.RecognisedActivityPack.Model;

import com.example.user.recognito.DataModels.RecognisedSong;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.TrackModel;

/**
 * Created by emmanuel on 12/30/2017.
 */

public interface DataManager{
    void saveSongInDb(/*Some model**/);
    void fetchSimilarTracks(TrackModel trackModel);
    void putRecognisedSongIntoDb(RecognisedSong recognisedSong);
}
