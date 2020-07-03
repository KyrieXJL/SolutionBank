package com.lenovo.smarttraffic;


import android.content.Intent;
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
public class Fragment06 extends Fragment {


    private ViewPager viewpager06;
    private RadioButton rb06_1;
    private RadioButton rb06_2;
    private RadioButton rb06_3;
    private RadioGroup rg06;
    private RadioButton rb06_4;
    private RadioButton rb06_5;
    private RadioButton rb06_6;
    private RadioButton rb06_7;
    private RadioButton rb06_8;
    private RadioButton rb06_9;

    public Fragment06() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment06, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        viewpager06 = (ViewPager) view.findViewById(R.id.viewpager06);
        rb06_1 = (RadioButton) view.findViewById(R.id.rb06_1);
        rb06_2 = (RadioButton) view.findViewById(R.id.rb06_2);
        rb06_3 = (RadioButton) view.findViewById(R.id.rb06_3);
        rb06_4 = (RadioButton) view.findViewById(R.id.rb06_4);
        rb06_5 = (RadioButton) view.findViewById(R.id.rb06_5);
        rb06_6 = (RadioButton) view.findViewById(R.id.rb06_6);
        rb06_7 = (RadioButton) view.findViewById(R.id.rb06_7);
        rb06_8 = (RadioButton) view.findViewById(R.id.rb06_8);
        rb06_9 = (RadioButton) view.findViewById(R.id.rb06_9);
        rg06 = (RadioGroup) view.findViewById(R.id.rg06);
        viewpager06.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        rg06.check(R.id.rb06_1);
                        fragment = new Fragment06_1();
                        break;
                    case 1:
                        fragment = new Fragment06_2();
                        break;
                    case 2:
                        fragment = new Fragment06_3();
                        break;
                    case 3:
                        fragment = new Fragment06_4();
                        break;
                    case 4:
                        fragment = new Fragment06_5();
                        break;
                    case 5:
                        fragment = new Fragment06_6();
                        break;
                    case 6:
                        fragment = new Fragment06_7();
                        break;
                    case 7:
                        fragment = new Fragment06_8();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 8;
            }
        });
        rg06.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb06_1) {
                    viewpager06.setCurrentItem(0);
                } else if (i == R.id.rb06_2) {
                    viewpager06.setCurrentItem(1);
                } else if (i == R.id.rb06_3) {
                    viewpager06.setCurrentItem(2);
                } else if (i == R.id.rb06_4) {
                    viewpager06.setCurrentItem(3);
                } else if (i == R.id.rb06_5) {
                    viewpager06.setCurrentItem(4);
                } else if (i == R.id.rb06_6) {
                    viewpager06.setCurrentItem(5);
                } else if (i == R.id.rb06_7) {
                    viewpager06.setCurrentItem(6);
                } else if (i == R.id.rb06_8) {
                    viewpager06.setCurrentItem(7);
                }else if (i==R.id.rb06_9){
                    Intent intent=new Intent(getContext(),PicActivity2.class);
                    startActivity(intent);
                }
            }
        });
        viewpager06.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        rg06.check(R.id.rb06_1);
                        break;
                    case 1:
                        rg06.check(R.id.rb06_2);
                        break;
                    case 2:
                        rg06.check(R.id.rb06_3);
                        break;
                    case 3:
                        rg06.check(R.id.rb06_4);
                        break;
                    case 4:
                        rg06.check(R.id.rb06_5);
                        break;
                    case 5:
                        rg06.check(R.id.rb06_6);
                        break;
                    case 6:
                        rg06.check(R.id.rb06_7);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }
}
