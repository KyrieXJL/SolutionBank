package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lenovo.smarttraffic.Adapter.Adapter30;
import com.lenovo.smarttraffic.Entity.Car02;
import com.lenovo.smarttraffic.Entity.Car30;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment30 extends Fragment {


    private GridView gridview30;
    private List<Car30> car30s;
    private Adapter30 adapter30;


    public Fragment30() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment30, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gridview30 = (GridView) view.findViewById(R.id.gridview30);
        car30s=new ArrayList<>();
        car30s.add(new Car30(R.drawable.car30,"视频1"));
        car30s.add(new Car30(R.drawable.car30,"视频2"));
        car30s.add(new Car30(R.drawable.car30,"视频3"));
        car30s.add(new Car30(R.drawable.car30,"视频4"));
        adapter30=new Adapter30(getContext(),R.layout.fragment30_lsit,car30s);
        gridview30.setAdapter(adapter30);
        gridview30.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Fragment30_1 fragment30_1=new Fragment30_1();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,fragment30_1).commit();
            }
        });
    }
}
