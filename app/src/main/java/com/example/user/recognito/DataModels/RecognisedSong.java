package com.example.user.recognito.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.TrackModel;

import java.util.List;

/**
 * Created by emmanuel on 12/26/2017.
 */

public class RecognisedSong implements Parcelable{
    private TrackModel trackModel;
    private String albumId;
    private String youTubeId;
    private String trackId;
    private List<Artist> artistIds;
    private long timeStamp;

    public RecognisedSong(String albumId, String youTubeId, String trackId, List<Artist> artistIds) {
        this.albumId = albumId;
        this.youTubeId = youTubeId;
        this.trackId = trackId;
        this.artistIds = artistIds;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTrackId() {
        return trackId;
    }

    public List<Artist> getArtistIds() {
        return artistIds;
    }

    public TrackModel getTrackModel() {
        return trackModel;
    }

    public String getYouTubeId() {
        return youTubeId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public RecognisedSong(TrackModel trackModel, String youTubeId) {
        this.trackModel = trackModel;
        this.youTubeId = youTubeId;
    }

    public RecognisedSong(String albumId, String youTubeId) {
        this.albumId = albumId;
        this.youTubeId = youTubeId;
    }

    // TODO: 12/26/2017 This object will contain: 1)The spotify album id and image url,Youtube id, deezer id, lyrics finder id

    protected RecognisedSong(Parcel in) {
        trackModel = in.readParcelable(TrackModel.class.getClassLoader());
        youTubeId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(trackModel, flags);
        dest.writeString(youTubeId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecognisedSong> CREATOR = new Creator<RecognisedSong>() {
        @Override
        public RecognisedSong createFromParcel(Parcel in) {
            return new RecognisedSong(in);
        }

        @Override
        public RecognisedSong[] newArray(int size) {
            return new RecognisedSong[size];
        }
    };
}
