package com.example.user.recognito.Fragments.ErrorFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.recognito.R;

/**
 * Created by emmanuel on 1/1/2018.
 */

public class MainActivityErrorFragment extends Fragment{
    private static final String ARGS_KEY = "key";
    private OnMainActivityErrorFragmentListener fragmentListener;

    public static MainActivityErrorFragment newInstance(String errorMessage) {
        Bundle args = new Bundle();
        args.putString(ARGS_KEY, errorMessage);
        MainActivityErrorFragment fragment = new MainActivityErrorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            fragmentListener = (OnMainActivityErrorFragmentListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException("This damn class cant be cast: implement the interface ass hole");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_activity_err_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews(view);

    }

    private void setUpViews(View view) {

        TextView errTexView = view.findViewById(R.id.err_reason);
        String errorMessage = getArguments().getString(ARGS_KEY);
        errTexView.setText(errorMessage);
        Button reTryButton = view.findViewById(R.id.start_recognition);
        reTryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentListener.onReTryButtonClicked();
            }
        });
    }

    public interface OnMainActivityErrorFragmentListener{
        void onReTryButtonClicked();
    }

}
