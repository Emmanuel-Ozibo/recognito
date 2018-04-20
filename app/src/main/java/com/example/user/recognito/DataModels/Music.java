package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by emmanuel on 12/19/2017.
 */

public class Music implements Parcelable{

    public ExternalIds external_ids;
    public String sample_begin_time_offset_ms;
    public String label;
    public ExternalMetaData external_metadata;
    public int play_offset_ms;
    public List<Artistm> artists;
    public String sample_end_time_offset_ms;
    public String release_date;
    public String title;
    public String db_end_time_offset_ms;
    public int duration_ms;
    public AlbumM album;
    public String acrid;
    public int result_from;
    public String db_begin_time_offset_ms;
    public int score;

    protected Music(Parcel in) {
        external_ids = in.readParcelable(ExternalIds.class.getClassLoader());
        sample_begin_time_offset_ms = in.readString();
        label = in.readString();
        external_metadata = in.readParcelable(ExternalMetaData.class.getClassLoader());
        play_offset_ms = in.readInt();
        artists = in.createTypedArrayList(Artistm.CREATOR);
        sample_end_time_offset_ms = in.readString();
        release_date = in.readString();
        title = in.readString();
        db_end_time_offset_ms = in.readString();
        duration_ms = in.readInt();
        album = in.readParcelable(AlbumM.class.getClassLoader());
        acrid = in.readString();
        result_from = in.readInt();
        db_begin_time_offset_ms = in.readString();
        score = in.readInt();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    public ExternalIds getExternal_ids() {
        return external_ids;
    }

    public String getSample_begin_time_offset_ms() {
        return sample_begin_time_offset_ms;
    }

    public String getLabel() {
        return label;
    }

    public ExternalMetaData getExternal_metadata() {
        return external_metadata;
    }

    public int getPlay_offset_ms() {
        return play_offset_ms;
    }

    public List<Artistm> getArtists() {
        return artists;
    }

    public String getSample_end_time_offset_ms() {
        return sample_end_time_offset_ms;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getDb_end_time_offset_ms() {
        return db_end_time_offset_ms;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public AlbumM getAlbum() {
        return album;
    }

    public String getAcrid() {
        return acrid;
    }

    public int getResult_from() {
        return result_from;
    }

    public String getDb_begin_time_offset_ms() {
        return db_begin_time_offset_ms;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(external_ids, i);
        parcel.writeString(sample_begin_time_offset_ms);
        parcel.writeString(label);
        parcel.writeParcelable(external_metadata, i);
        parcel.writeInt(play_offset_ms);
        parcel.writeTypedList(artists);
        parcel.writeString(sample_end_time_offset_ms);
        parcel.writeString(release_date);
        parcel.writeString(title);
        parcel.writeString(db_end_time_offset_ms);
        parcel.writeInt(duration_ms);
        parcel.writeParcelable(album, i);
        parcel.writeString(acrid);
        parcel.writeInt(result_from);
        parcel.writeString(db_begin_time_offset_ms);
        parcel.writeInt(score);
    }
}
