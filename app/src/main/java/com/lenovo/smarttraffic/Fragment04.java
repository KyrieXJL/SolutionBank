package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment04 extends Fragment implements View.OnClickListener {


    private Button btn04_1;
    private Button btn04_2;
    private LinearLayout linear04;
    private FragmentManager fragmentManager;

    public Fragment04() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment04, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btn04_1 = (Button) view.findViewById(R.id.btn04_1);
        btn04_2 = (Button) view.findViewById(R.id.btn04_2);
        linear04 = (LinearLayout) view.findViewById(R.id.linear04);

        btn04_1.setOnClickListener(this);
        btn04_2.setOnClickListener(this);
        fragmentManager=getFragmentManager();
        Fragment04_1 fragment04_1=new Fragment04_1();
        fragmentManager.beginTransaction().replace(R.id.linear04,fragment04_1).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn04_1:
                Fragment04_1 fragment04_1=new Fragment04_1();
                fragmentManager.beginTransaction().replace(R.id.linear04,fragment04_1).commit();

                break;
            case R.id.btn04_2:
                Fragment04_2 fragment04_2=new Fragment04_2();
                fragmentManager.beginTransaction().replace(R.id.linear04,fragment04_2).commit();

                break;
        }
    }
}
