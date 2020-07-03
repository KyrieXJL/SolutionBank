package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment16 extends Fragment {


    private TextView textbtn16_1;
    private TextView textbtn16_2;
    private TextView textbtn16_3;
    private LinearLayout linear16;
    private FragmentManager fragmentManager;

    public Fragment16() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment16, container, false);
        initView(view);


        return view;
    }

    private void initView(View view) {
        textbtn16_1 = (TextView) view.findViewById(R.id.textbtn16_1);
        textbtn16_2 = (TextView) view.findViewById(R.id.textbtn16_2);
        textbtn16_3 = (TextView) view.findViewById(R.id.textbtn16_3);
        linear16 = (LinearLayout) view.findViewById(R.id.linear16);
        fragmentManager=getFragmentManager();
        Fragment16_1 fragment16_1=new Fragment16_1();
        fragmentManager.beginTransaction().replace(R.id.linear16,fragment16_1).commit();
        textbtn16_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager=getFragmentManager();
                Fragment16_1 fragment16_1=new Fragment16_1();
                fragmentManager.beginTransaction().replace(R.id.linear16,fragment16_1).commit();
            }
        });
        textbtn16_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment16_2 fragment16_2=new Fragment16_2();
                fragmentManager.beginTransaction().replace(R.id.linear16,fragment16_2).commit();
            }
        });
        textbtn16_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment16_3 fragment16_3=new Fragment16_3();
                fragmentManager.beginTransaction().replace(R.id.linear16,fragment16_3).commit();
            }
        });
    }
}
