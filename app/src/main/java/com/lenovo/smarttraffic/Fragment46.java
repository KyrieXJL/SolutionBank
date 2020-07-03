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
public class Fragment46 extends Fragment {


    private LinearLayout linear46;
    private TextView textbtn46_1;
    private TextView textbtn46_2;
    private FragmentManager fragmentManager;

    public Fragment46() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment46, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        linear46 = (LinearLayout) view.findViewById(R.id.linear46);
        textbtn46_1 = (TextView) view.findViewById(R.id.textbtn46_1);
        textbtn46_2 = (TextView) view.findViewById(R.id.textbtn46_2);
        fragmentManager=getFragmentManager();
        Fragment46_1 fragment46_1=new Fragment46_1();
        fragmentManager.beginTransaction().replace(R.id.linear46,fragment46_1).commit();
        textbtn46_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment46_1 fragment46_1=new Fragment46_1();
                fragmentManager.beginTransaction().replace(R.id.linear46,fragment46_1).commit();

            }
        });
    }
}
