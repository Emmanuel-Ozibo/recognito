package com.example.user.recognito.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;

import com.example.user.recognito.DataModels.DataBaseSongModel;
import com.example.user.recognito.DataModels.RecognisedSong;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.LAlbum;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.LArtist;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.LImage;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.Tags;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.TopTags;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.TrackModel;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.Wiki;
import com.example.user.recognito.Utils.Constant;
import com.example.user.recognito.Utils.ToastMessageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmanuel on 12/23/2017.
 */


public class RecognitoDb extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABASE_NAME = "recognito.db";
    private static final int VERSION = 1;


    public RecognitoDb(Context context){
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_DATABASE = "CREATE TABLE " + RecognitoContract.DATABASE_NAME + " (" +
                RecognitoContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RecognitoContract.ALBUM_TITLE + " TEXT, " +
                RecognitoContract.MUSIC_TITLE + " TEXT, " +
                RecognitoContract.DURATION + " INTEGER, " +
                RecognitoContract.IMAGE_URL + " TEXT, " +
                RecognitoContract.MUSIC_TIME_STAMP + " TIMESTAMP DEFAULT CURRENT TIMESTAMP " +
                " );";
        sqLiteDatabase.execSQL(CREATE_DATABASE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecognitoContract.DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertRecognisedSongIntoDb(RecognisedSong recognisedSong){
        long id = 0L;
        //todo: store the main item in the database, when the user clicks the item view, it will sync to the server again to get the remaining data
        TrackModel trackModel = recognisedSong.getTrackModel();
        if (trackModel != null){
            String musicTitle = trackModel.name;int duration = trackModel.duration;String albumTitle = trackModel.getName();
            String imageUrl = trackModel.getImageModelList().get(2).getUrl();
            long timeStamp = recognisedSong.getTimeStamp();
            //bundle them into content value
            ContentValues cvs = new ContentValues();
            cvs.put(RecognitoContract.ALBUM_TITLE, albumTitle);
            cvs.put(RecognitoContract.MUSIC_TITLE, musicTitle);
            cvs.put(RecognitoContract.DURATION, duration);
            cvs.put(RecognitoContract.IMAGE_URL, imageUrl);
            cvs.put(RecognitoContract.MUSIC_TIME_STAMP, " time('now') ");

            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            id = sqLiteDatabase.insert(RecognitoContract.DATABASE_NAME, null, cvs);
        }
        return id;
    }

    public List<DataBaseSongModel> getSongsFromDb(){
        Cursor cursor = getMusicCursor();
        return createSongsObject(cursor);
    }

    public DataBaseSongModel getSingleSongFromDb(int position){
        Cursor cursor = getMusicCursor();
        return createSongObject(cursor, position);
    }

    private DataBaseSongModel createSongObject(Cursor cursor, int position) {
        DataBaseSongModel dataBaseSongModel = null;
        if (cursor != null){
            if (cursor.moveToPosition(position)){
                String songTitle = cursor.getString(cursor.getColumnIndex(RecognitoContract.MUSIC_TITLE));
                String albumTitle = cursor.getString(cursor.getColumnIndex(RecognitoContract.ALBUM_TITLE));
                int duration = cursor.getInt(cursor.getColumnIndex(RecognitoContract.DURATION));
                String imageUrl = cursor.getString(cursor.getColumnIndex(RecognitoContract.IMAGE_URL));
                long timeStamp = cursor.getLong(cursor.getColumnIndex(RecognitoContract.MUSIC_TIME_STAMP));
                dataBaseSongModel = new DataBaseSongModel(songTitle, albumTitle, duration, imageUrl);
                dataBaseSongModel.setTimeStamp(timeStamp);
            }
        }
        return dataBaseSongModel;
    }


    private List<DataBaseSongModel> createSongsObject(Cursor cursor){
        List<DataBaseSongModel> songModelList = new ArrayList<>();
        while (cursor.moveToNext()){
            String songTitle = cursor.getString(cursor.getColumnIndex(RecognitoContract.MUSIC_TITLE));
            String albumTitle = cursor.getString(cursor.getColumnIndex(RecognitoContract.MUSIC_TITLE));
            int duration = cursor.getInt(cursor.getColumnIndex(RecognitoContract.DURATION));
            String imageUrl = cursor.getString(cursor.getColumnIndex(RecognitoContract.IMAGE_URL));
//            long timeStamp = cursor.getLong(cursor.getColumnIndex(RecognitoContract.MUSIC_TIME_STAMP));
            DataBaseSongModel songModel = new DataBaseSongModel(songTitle, albumTitle, duration, imageUrl);
//            songModel.setTimeStamp(timeStamp);
            songModelList.add(songModel);
        }
        return songModelList;
    }

    private Cursor getMusicCursor() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(RecognitoContract.DATABASE_NAME, null, null,
                null, null, null, RecognitoContract._ID);
//        cursor.close();
        return cursor;
    }

    private class RecognitoContract implements BaseColumns{
        //name of data base
        public static final String DATABASE_NAME = "recognito";
        // access token
        public static final String MUSIC_TITLE = "music_title";
        //duration
        public static final String DURATION = "duration";
        //album title
        public static final String ALBUM_TITLE = "album_title";
        //image url
        public static final String IMAGE_URL = "inage_url";
        //time stamp
        public static final String MUSIC_TIME_STAMP = "time_stamp";

    }
}