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

    public DashBoardAdapter(Context context, List<DataBaseSongModel> dataBaseSongModels) {
        this.dataBaseSongModels = dataBaseSongModels;
        this.context = context;
    }

    @Override
    public DashBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.dash_board_item_view, parent, false);
        return new DashBoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DashBoardViewHolder holder, int position) {
        holder.onBindViews(position);
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


        public DashBoardViewHolder(View itemView) {
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
            long timeStamp = dataBaseSongModels.get(position).getTimeStamp();

            String durationStr = getDurationStr(duration);

            songTitle_tv.setText(songTitle);
            albumTitle_tv.setText(durationStr);
            duration_tv.setText("");

            setImage(imageUrl);
        }

        private void getElapseTimeInMills(long initTime){
            long currentTime = System.currentTimeMillis();
            Calendar calendarInit = Calendar.getInstance();
            Calendar calendarNow = Calendar.getInstance();
            long timeIntervel = currentTime - initTime;
            calendarInit.setTimeInMillis(initTime);
            calendarNow.setTimeInMillis(currentTime);

            calendarInit.get(Calendar.HOUR);
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
