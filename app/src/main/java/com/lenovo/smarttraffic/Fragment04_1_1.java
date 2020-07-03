package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment04_1_1 extends Fragment {


    private VideoView videoview04;

    public Fragment04_1_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment04_1_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        videoview04 = (VideoView) view.findViewById(R.id.videoview04);
        String url="android.resource://"+getContext().getPackageName()+"/"+R.raw.a2;
        videoview04.setVideoPath(url);
        MediaController mediaController=new MediaController(getContext());
        videoview04.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoview04);
        videoview04.start();
    }
}
