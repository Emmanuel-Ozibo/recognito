package com.example.user.recognito.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.user.recognito.LastFmApiWapper.LastFmModels.SimilarTrack.STrack;
import com.example.user.recognito.R;
import com.example.user.recognito.Utils.ImageLoadingUtil;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Track;

import java.util.List;

/**
 * Created by emmanuel on 12/31/2017.
 */



public class CustomGridAdapter extends ArrayAdapter<Track>{
    private Context context;private int Layoutres;
    private List<Track>trackList;
    private ImageView songPoster;

    public CustomGridAdapter(@NonNull Context context, int resource, @NonNull List<Track> trackList) {
        super(context, resource, trackList);
        this.context = context;this.Layoutres = resource;
        this.trackList = trackList;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(Layoutres, parent, false);

            String similarSongTitle = trackList.get(position).getName();
            String similarSongImageUrl = trackList.get(position).getAlbum().getImages().get(0).getUrl();

            TextView songTitleTv = convertView.findViewById(R.id.similar_song_title);
            songPoster = convertView.findViewById(R.id.similar_song_poster);

            songTitleTv.setText(similarSongTitle);

            //loads image into the image view
            loadImageIntoView(similarSongImageUrl);

        }
        return convertView;

    }

    private void loadImageIntoView(final String similarSongImageUrl) {
        ImageLoadingUtil.loadImage(context, similarSongImageUrl, new ImageLoadingUtil.BitmapLoadedListener() {
            @Override
            public void onLoad(Bitmap originalBitmap){
                songPoster.setImageBitmap(originalBitmap);
                if (originalBitmap == null){
                    loadImageIntoView(similarSongImageUrl);
                }
            }
        });
    }


    @Override
    public int getCount(){
        int numberOfItems = trackList.size();
        if (numberOfItems > 10){
            return 10;
        }else {
            trackList.size();
        }
        return 10;
    }
}
