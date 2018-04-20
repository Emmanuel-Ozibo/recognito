package com.example.user.recognito.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.recognito.R;
import com.google.android.youtube.player.YouTubePlayer;

/**
 * Created by emmanuel on 12/20/2017.
 */

public class PreRecogniseFragment extends Fragment{
    private LinearLayout linearLayout;


    public static PreRecogniseFragment newInstance(String artistSong, String songTitle) {
        Bundle args = new Bundle();
        args.putString("artistSong", artistSong);
        args.putString("songTitle", songTitle);
        PreRecogniseFragment fragment = new PreRecogniseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pre_recog_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String mArtistSong = bundle.getString("artistSong");
        String mSongTitle = bundle.getString("songTitle");
        String finalText = mArtistSong + " By " + mSongTitle;
        TextView artistSong = view.findViewById(R.id.artistSong);
        artistSong.setText(finalText);

        //show the progress bar immediately
        linearLayout = view.findViewById(R.id.progressbar_container);
        linearLayout.setVisibility(View.VISIBLE);

//        onViewCreated.viewCreated();

    }

    @Override
    public void onPause() {
        super.onPause();
        linearLayout.setVisibility(View.GONE);
    }
}
