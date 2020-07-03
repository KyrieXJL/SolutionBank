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
public class Fragment30_1 extends Fragment {


    private VideoView videoview30;

    public Fragment30_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment30_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        videoview30 = (VideoView) view.findViewById(R.id.videoview30);
        String url="android.resource://"+getContext().getPackageName()+"/"+R.raw.a2;
        videoview30.setVideoPath(url);
        MediaController mediaController=new MediaController(getContext());

        videoview30.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoview30);
        videoview30.start();
    }
}
