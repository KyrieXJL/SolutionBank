package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment34 extends Fragment {


    private ImageView btn34_1;
    private ImageView btn34_2;
    private ImageView btn34_3;
    private FragmentManager fragmentManager;

    public Fragment34() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment34, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btn34_1 = (ImageView) view.findViewById(R.id.btn34_1);
        btn34_2 = (ImageView) view.findViewById(R.id.btn34_2);
        btn34_3 = (ImageView) view.findViewById(R.id.btn34_3);
        fragmentManager=getFragmentManager();
        btn34_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment34_1 fragment34_1=new Fragment34_1();
                fragmentManager.beginTransaction().replace(R.id.container,fragment34_1).commit();
            }
        });
        btn34_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment34_2 fragment34_2=new Fragment34_2();
                fragmentManager.beginTransaction().replace(R.id.container,fragment34_2).commit();
            }
        });

        btn34_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment34_3 fragment34_3=new Fragment34_3();
                fragmentManager.beginTransaction().replace(R.id.container,fragment34_3).commit();
            }
        });
    }
}
