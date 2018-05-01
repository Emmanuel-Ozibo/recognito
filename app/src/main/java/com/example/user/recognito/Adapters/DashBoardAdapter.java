package com.example.user.recognito.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.recognito.DataModels.DataBaseSongModel;
import com.example.user.recognito.R;
import com.example.user.recognito.Utils.ImageLoadingUtil;

import java.util.Calendar;
import java.util.List;

/**
 * @author Created by emmanuel on 12/13/2017.
 */

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder>{
    private List<DataBaseSongModel> dataBaseSongModels;
    private Context context;
    private static final int MUSIC_ITEM_VIEW = 0;
    private static final int HISTORY_ITEM_VIEW = 1;


    public DashBoardAdapter(Context context, List<DataBaseSongModel> dataBaseSongModels) {
        this.dataBaseSongModels = dataBaseSongModels;
        this.context = context;
    }

    @Override
    public DashBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        int layoutRes = 0;
        switch (viewType){
            case HISTORY_ITEM_VIEW:
                layoutRes = R.layout.history_item_view;
                break;
            case MUSIC_ITEM_VIEW:
                layoutRes = R.layout.dash_board_item_view;
                break;
        }
        View view = LayoutInflater.from(context).inflate(layoutRes, parent, false);
        return new DashBoardViewHolder(view, viewType);
    }

    @Override
    public int getItemViewType(int position){
        if (position == 0){
            return HISTORY_ITEM_VIEW;
        }else {
            return MUSIC_ITEM_VIEW;
        }
    }

    @Override
    public void onBindViewHolder(DashBoardViewHolder holder, int position){
        int type = holder.getItemViewType();
        if (type != HISTORY_ITEM_VIEW){
            holder.onBindViews(position);
        }
    }

    @Override
    public int getItemCount(){
        if (dataBaseSongModels == null){
            return 0;
        }
        return dataBaseSongModels.size();
    }

    public class DashBoardViewHolder extends RecyclerView.ViewHolder{
        private TextView songTitle_tv, albumTitle_tv, duration_tv;
        private ImageView poster_imageView;


        public DashBoardViewHolder(View itemView, int viewType){
            super(itemView);
            songTitle_tv = itemView.findViewById(R.id.song_title_tv);
            albumTitle_tv = itemView.findViewById(R.id.album_duration_tv);
            duration_tv = itemView.findViewById(R.id.timestamp_tv);
            poster_imageView = itemView.findViewById(R.id.album_art_mv);
        }

        void onBindViews(int position){
            String songTitle = dataBaseSongModels.get(position).getSongTitle();
            String albumTitle = dataBaseSongModels.get(position).getAlbumTitle();
            int duration = dataBaseSongModels.get(position).getDuration();
            String imageUrl = dataBaseSongModels.get(position).getImageUrl();
//            long timeStamp = dataBaseSongModels.get(position).getTimeStamp();
            long timeStamp = System.currentTimeMillis();

            String durationStr = getDurationStr(duration);

            songTitle_tv.setText(songTitle);
            albumTitle_tv.setText(durationStr);
            duration_tv.setText(getDate(timeStamp));

            setImage(imageUrl);
        }

        private String getDate(long initTime){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(initTime);
            //Format -> dd-mm-yyyy
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH)+1;
            int year = calendar.get(Calendar.YEAR);
            return day + "-" +month+"-" + year;
        }

        private String getDurationStr(int durationMilsecs){
            int duration = durationMilsecs/1000;
            int munites = duration / 60;
            int seconds = duration - (munites*60);
            return munites + ":" + seconds;
        }

        private void setImage(String imageUrl) {
            ImageLoadingUtil.loadImage(context, imageUrl, new ImageLoadingUtil.BitmapLoadedListener() {
                @Override
                public void onLoad(Bitmap originalBitmap) {
                    poster_imageView.setImageBitmap(originalBitmap);
                }
            });
        }
    }
}
