package com.example.user.recognito.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.recognito.Adapters.DashBoardAdapter;
import com.example.user.recognito.DataModels.DataBaseSongModel;
import com.example.user.recognito.R;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by emmanuel on 1/4/2018.
 */


public class DashBoardFragment extends Fragment{
    private OnDashBoardFragmentListener boardFragmentListener;
    private static final String SONGS_KEY = "key";

    public static DashBoardFragment newInstance(List<DataBaseSongModel> dataBaseSongModelList) {

        Bundle args = new Bundle();
        args.putParcelableArrayList(SONGS_KEY, (ArrayList<? extends Parcelable>) dataBaseSongModelList);
        DashBoardFragment fragment = new DashBoardFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            boardFragmentListener = (OnDashBoardFragmentListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException("Can't Cast this class");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dash_board_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.dash_board_rv);
        DashBoardAdapter adapter = new DashBoardAdapter(getContext(), getArguments().<DataBaseSongModel>getParcelableArrayList(SONGS_KEY));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    public interface OnDashBoardFragmentListener{
        void onItemClicked(int position);
    }
}
