package com.example.user.recognito.Fragments;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.customviewtestmodule.RecogniseButtonView;
import com.example.user.recognito.R;

/**
 * Created by emmanuel on 12/12/2017.
 */


public class FirstFragment extends Fragment implements View.OnClickListener{
    private OnFirstFragmantAttached attached;
    private RecogniseButtonView buttonView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            attached = (OnFirstFragmantAttached) context;
        }catch (ClassCastException e){
            throw new ClassCastException("This class cant be cast: must implement interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.first_frag_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonView = view.findViewById(R.id.recognition_button);
        ImageView dash_board = view.findViewById(R.id.dashboard_mv);
        ImageView about = view.findViewById(R.id.about_mv);
        ImageView settings = view.findViewById(R.id.settings);
        settings.setOnClickListener(this);
        dash_board.setOnClickListener(this);about.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        startViewAnimation();
    }


    private void startViewAnimation(){
        int heightAnim = buttonView.getHeight()/8;
        ValueAnimator animator= ValueAnimator.ofInt(0, heightAnim);
        animator.setDuration(700);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

//                buttonView.setTranslationY(valueAnimator.getAnimatedValue());
            }

        });
        animator.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.recognition_button:
                attached.onRecognitionButtonClicked();
                break;
            case R.id.dashboard_mv:
                attached.onDashboardClicked();
                break;
            case R.id.about_mv:
                attached.onAboutClicked();
                break;
            case R.id.settings:
                attached.onSettingsClicked();
        }
    }

    public interface OnFirstFragmantAttached{
        void onRecognitionButtonClicked();
        void onDashboardClicked();
        void onAboutClicked();
        void onSettingsClicked();
    }
}