package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment45_1 extends Fragment {


    private GridView gridview45_1;

    public Fragment45_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment45_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gridview45_1 = (GridView) view.findViewById(R.id.gridview45_1);
    }
}
