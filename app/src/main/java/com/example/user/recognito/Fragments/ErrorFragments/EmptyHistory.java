package com.example.user.recognito.Fragments.ErrorFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.recognito.R;

/**
 * Created by emmanuel on 2018-04-17.
 */

public class EmptyHistory extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.empty_history_fragment_layout, container, false);
    }

}
