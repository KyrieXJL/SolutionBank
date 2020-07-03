package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment15 extends Fragment {


    private ViewPager vp15;
    private RadioButton rb15_1;
    private RadioButton rb15_2;
    private RadioButton rb15_3;
    private RadioButton rb15_4;
    private RadioButton rb15_5;
    private RadioGroup rg15;

    public Fragment15() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment15, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        vp15 = (ViewPager) view.findViewById(R.id.vp15);
        rb15_1 = (RadioButton) view.findViewById(R.id.rb15_1);
        rb15_2 = (RadioButton) view.findViewById(R.id.rb15_2);
        rb15_3 = (RadioButton) view.findViewById(R.id.rb15_3);
        rb15_4 = (RadioButton) view.findViewById(R.id.rb15_4);
        rb15_5 = (RadioButton) view.findViewById(R.id.rb15_5);
        rg15 = (RadioGroup) view.findViewById(R.id.rg15);
        vp15.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0:
                        rg15.check(R.id.rb15_1);
                        fragment=new Fragment15_1();
                        break;
                    case 1:
                        fragment=new Fragment15_2();
                        break;
                    case 2:
                        fragment=new Fragment15_3();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        rg15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i==R.id.rb15_1){
                    vp15.setCurrentItem(0);
                }else if (i==R.id.rb15_2){
                    vp15.setCurrentItem(1);
                }else if (i==R.id.rb15_3){
                    vp15.setCurrentItem(2);
                }
            }
        });
        vp15.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        rg15.check(R.id.rb15_1);
                        break;
                    case 1:
                        rg15.check(R.id.rb15_2);
                        break;
                    case 2:
                        rg15.check(R.id.rb15_3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
