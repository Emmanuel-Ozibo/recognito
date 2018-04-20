package com.example.user.recognito.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.recognito.Activities.RecognisedActivityPack.Model.Presenter;
import com.example.user.recognito.Activities.RecognisedActivityPack.RecognisedContract;
import com.example.user.recognito.Adapters.CustomGridAdapter;
import com.example.user.recognito.DataModels.RecognisedSong;
import com.example.user.recognito.DataModels.ShareContent;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.SimilarTrack.SimilarTrack;
import com.example.user.recognito.LastFmApiWapper.LastFmModels.Track.TrackModel;
import com.example.user.recognito.R;
import com.example.user.recognito.Utils.Constant;
import com.example.user.recognito.Utils.DisplayProperties;
import com.example.user.recognito.Utils.ToastMessageUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Track;

import java.util.Calendar;
import java.util.List;

/**
 * Created by emmanuel on 12/12/2017.
 */


public class RecognisedFragment extends Fragment implements RecognisedContract.RecognisedFragmentView,
        View.OnClickListener{

    private Context context;
    private static final String RECOGNISED_SONG_KEY = "key";
    private Presenter presenter;
    private OnRecognisedFragmentListener recognisedFragmentListener;
    private TextView musicTv;
    private ImageView musicPoster,largeMusicPoster;
    private NestedScrollView nestedScrollView;
    private static final int ANIMATION_TIME = 3000;


    public static RecognisedFragment newInstance(RecognisedSong recognisedSong) {
        Bundle args = new Bundle();
        args.putParcelable(RECOGNISED_SONG_KEY, recognisedSong);
        RecognisedFragment fragment = new RecognisedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        presenter = new Presenter(context,this);
        //connect the listener to this fragment
        try{
            recognisedFragmentListener = (OnRecognisedFragmentListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException("Can't cast this shitty class");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recognised_fragment_layout, container, false);

        setUpViewsAndListeners(view);

        return view;
    }

    private void setUpViewsAndListeners(View view) {
        nestedScrollView = view.findViewById(R.id.nested_scrollView);
        musicTv = view.findViewById(R.id.music_title);
        musicPoster = view.findViewById(R.id.poster);
        largeMusicPoster = view.findViewById(R.id.music_poster);
        FloatingActionButton fab = view.findViewById(R.id.rec_fab);
        ImageView backButton = view.findViewById(R.id.back_btn);
        ImageView dashBoard = view.findViewById(R.id.dashboard_mv_rec);
        YouTubeThumbnailView thumbnailView = view.findViewById(R.id.youtube_thumbnail_view);
        thumbnailView.setOnClickListener(this);
        backButton.setOnClickListener(this);
        dashBoard.setOnClickListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        RecognisedSong recognisedSong = getArguments().getParcelable(RECOGNISED_SONG_KEY);
        long timeStamp = System.currentTimeMillis();
        recognisedSong.setTimeStamp(timeStamp);
        presenter.insertSongIntoDb(recognisedSong);
        setUpImageViewsAndText(recognisedSong, view);
//        startAnimation();
    }

    private void startAnimation(){
        float poster_height = context.getResources().getDimension(R.dimen.recognise_frag_poster_height);
        int screenWidth = DisplayProperties.getScreenWidth(context);
        int screenHeight = DisplayProperties.getScreenHeight(context);
        float initialScroolViewPosition = screenHeight - poster_height;
        int scrollViewHeight = nestedScrollView.getHeight();
        nestedScrollView.setTranslationX(-screenWidth);
        nestedScrollView.animate()
                .setDuration(ANIMATION_TIME)
                .setInterpolator(new DecelerateInterpolator())
                .translationY(screenWidth)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startFabAnimation();
                    }

                    private void startFabAnimation() {
                        //todo: animated fab
                    }
                })
                .start();

    }

    private void setUpImageViewsAndText(RecognisedSong recognisedSong, View view) {
        if (recognisedSong != null){
            TrackModel trackModel = recognisedSong.getTrackModel();
            musicTv.setText(trackModel.name);
            presenter.getImageBitmaps(context, trackModel.imageModelList.get(0).getUrl());
           // presenter.getSimilarArtist(trackModel.getArtistIds());
            setUpYouTubeThumbnail(view, recognisedSong.getYouTubeId());
        }
    }

    private void setUpYouTubeThumbnail(View view,  String youTubeId) {
        YouTubeThumbnailView thumbnailView = view.findViewById(R.id.youtube_thumbnail_view);
        thumbnailView.initialize(Constant.YOUTUBE_API_KEY, new YouTubeThumbnailViewImple(youTubeId));
    }

    @Override
    public void blurBitmapGenerated(Bitmap blurBitmap) {
        //set the larger poster to be blur
        largeMusicPoster.setImageBitmap(blurBitmap);
    }

    @Override
    public void circularBitmapGenerated(Bitmap circularBitmap) {
        //set the smaller image to be small
        musicPoster.setImageBitmap(circularBitmap);
    }

    @Override
    public void topTracks(List<Track> topTrackList){
        //todo:start populate the views: similar tracks
        View itemview = getView();
        if (itemview != null){
            GridView horizontalGridView = itemview.findViewById(R.id.horizontal_grid);
            setUpParams(horizontalGridView, topTrackList);
        }

    }

    private void setUpParams(GridView horizontalGridView, List<Track> topTrackList) {
        int itemResLayout = R.layout.similar_songs_item_view;
        CustomGridAdapter customGridAdapter = new CustomGridAdapter(context, itemResLayout, topTrackList);
        float itemWidth = context.getResources().getDimensionPixelSize(R.dimen.similar_item_width);
        itemWidth = Math.round(itemWidth);
        float density = getScreenDensity();
        int totalWidth = (int) (itemWidth * customGridAdapter.getCount());
        int singleWidth = (int) (itemWidth);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(totalWidth,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        setUpHorizontalGridView(singleWidth, params, horizontalGridView,customGridAdapter);
    }

    private float getScreenDensity() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null)
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    private void setUpHorizontalGridView(int singleWidth, LinearLayout.LayoutParams params, GridView horizontalGridView, CustomGridAdapter customGridAdapter) {
        horizontalGridView.setLayoutParams(params);
        horizontalGridView.setColumnWidth(singleWidth);
        horizontalGridView.setStretchMode(GridView.STRETCH_SPACING);
        horizontalGridView.setNumColumns(customGridAdapter.getCount());
        horizontalGridView.setAdapter(customGridAdapter);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rec_fab){
            recognisedFragmentListener.onShareButtonClicked(null);
        }else if (view.getId() == R.id.back_btn){
            recognisedFragmentListener.onBackButtonClicked();
        }else if (view.getId() == R.id.youtube_thumbnail_view){
            recognisedFragmentListener.onYouTubeThumbnailViewClicked();
        }else if (view.getId() == R.id.dashboard_mv_rec){
            recognisedFragmentListener.onDashBoardClicked();
        }
    }


    public interface OnRecognisedFragmentListener{
        void onShareButtonClicked(@Nullable ShareContent shareContent);
        void onBackButtonClicked();
        void onYouTubeThumbnailViewClicked();
        void onDashBoardClicked();
    }


    private class YouTubeThumbnailViewImple implements YouTubeThumbnailView.OnInitializedListener{
        private String youTubeId;

        private YouTubeThumbnailViewImple(String youTubeId){
            this.youTubeId = youTubeId;
        }

        @Override
        public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
            youTubeThumbnailLoader.setVideo(youTubeId);
            youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                @Override
                public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                    youTubeThumbnailLoader.release();
                }

                @Override
                public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                    //todo: Try to reload
                }
            });
        }

        @Override
        public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

        }
    }
}