package com.example.user.recognito.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.recognito.R;
import com.skyfishjy.library.RippleBackground;

/**
 * Created by emmanuel on 12/10/2017.
 */



public class SoundRecognitionAnim extends Fragment{
    private RippleBackground rippleBackground;
    private OnSoundRecognitionAminAttached recognitionAminAttached;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            recognitionAminAttached = (OnSoundRecognitionAminAttached) context;
        }catch (ClassCastException e){
            throw new ClassCastException("Cant cast this class");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.animation_frag_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rippleBackground = view.findViewById(R.id.ripple_content);
        rippleBackground.startRippleAnimation();
        ImageView cancelRecognition = view.findViewById(R.id.cancel_recognito);
        cancelRecognition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recognitionAminAttached.onCancelClicked();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        rippleBackground.stopRippleAnimation();
    }

    public interface OnSoundRecognitionAminAttached{
        void onCancelClicked();
    }
}
