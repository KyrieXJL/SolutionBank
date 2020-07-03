package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment45 extends Fragment {


    private LinearLayout linear45;
    private TextView textbtn45_1;
    private TextView textbtn45_2;
    private TextView textbtn45_3;

    public Fragment45() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment45, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        linear45 = (LinearLayout) view.findViewById(R.id.linear45);
        textbtn45_1 = (TextView) view.findViewById(R.id.textbtn45_1);
        textbtn45_2 = (TextView) view.findViewById(R.id.textbtn45_2);
        textbtn45_3 = (TextView) view.findViewById(R.id.textbtn45_3);
    }
}
