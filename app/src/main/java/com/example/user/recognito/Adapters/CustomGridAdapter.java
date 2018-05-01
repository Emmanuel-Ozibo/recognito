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

import com.example.user.recognito.DataModels.SpotifyData.Album;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.SimilarTrack.STrack;
import com.example.user.recognito.R;
import com.example.user.recognito.Utils.ImageLoadingUtil;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Track;

import java.util.List;

/**
 * Created by emmanuel on 12/31/2017.
 */


public class CustomGridAdapter extends ArrayAdapter<String>{
    private Context context;
    private int Layoutres;
    private List<String>availableMarkets;



    public CustomGridAdapter(@NonNull Context context, int resource, @NonNull List<String> availiableMarkets) {
        super(context, resource, availiableMarkets);
        this.context = context;this.Layoutres = resource;
        this.availableMarkets = availiableMarkets;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(Layoutres, parent, false);
            TextView marketTitle = convertView.findViewById(R.id.markets_tv);
            marketTitle.setText(availableMarkets.get(position));

        }
        return convertView;
    }

    @Override
    public int getCount(){
        if (!availableMarkets.isEmpty() && availableMarkets != null){
            return availableMarkets.size();
        }
        return 0;
    }
}
