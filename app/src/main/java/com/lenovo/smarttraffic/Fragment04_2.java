package com.lenovo.smarttraffic;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class Fragment04_2 extends Fragment {


    private GridView gridview04;
    private List<Car04> car04s;
    private Adapter04_1 adapter04_1;


    public Fragment04_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment04_2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gridview04 = (GridView) view.findViewById(R.id.gridview04);
        car04s=new ArrayList<>();
        car04s.add(new Car04("图片1",R.drawable.car04));
        car04s.add(new Car04("图片2",R.drawable.car04));
        car04s.add(new Car04("图片3",R.drawable.car04));
        car04s.add(new Car04("图片4",R.drawable.car04));
        adapter04_1=new Adapter04_1(getContext(),R.layout.fragment04_list,car04s);
        gridview04.setAdapter(adapter04_1);
        gridview04.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(),PicActivity.class);
                startActivity(intent);
            }
        });
        gridview04.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        break;


                }
            }
        });
    }
}
