package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lenovo.smarttraffic.Adapter.Adapter04_1;
import com.lenovo.smarttraffic.Entity.Car04;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment04_1 extends Fragment {


    private GridView gridview04;
    private List<Car04> car04s;
    private Adapter04_1 adapter04_1;

    public Fragment04_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment04_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gridview04 = (GridView) view.findViewById(R.id.gridview04);
        car04s=new ArrayList<>();
        car04s.add(new Car04("视频1",R.drawable.car04));
        car04s.add(new Car04("视频2",R.drawable.car04));
        car04s.add(new Car04("视频3",R.drawable.car04));
        car04s.add(new Car04("视频4",R.drawable.car04));
        adapter04_1=new Adapter04_1(getContext(),R.layout.fragment04_list,car04s);
        gridview04.setAdapter(adapter04_1);
        gridview04.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment04_1_1 fragment04_1_1=new Fragment04_1_1();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,fragment04_1_1).commit();

            }
        });
    }
}
