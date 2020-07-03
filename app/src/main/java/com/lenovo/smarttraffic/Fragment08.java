package com.lenovo.smarttraffic;


import android.app.DatePickerDialog;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment08 extends Fragment {


    private TextView text08_1;
    private TextView text08_2;
    private TextView cxtext08_1;
    private Switch switch08_1;
    private TextView cxtext08_2;
    private Switch switch08_2;
    private TextView cxtext08_3;
    private Switch switch08_3;
    private ImageView imageView;
 //   private AnimationDrawable animationDrawable;

    public Fragment08() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment08, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text08_1 = (TextView) view.findViewById(R.id.text08_1);
        text08_2 = (TextView) view.findViewById(R.id.text08_2);
        cxtext08_1 = (TextView) view.findViewById(R.id.cxtext08_1);
        switch08_1 = (Switch) view.findViewById(R.id.switch08_1);
        cxtext08_2 = (TextView) view.findViewById(R.id.cxtext08_2);
        switch08_2 = (Switch) view.findViewById(R.id.switch08_2);
        cxtext08_3 = (TextView) view.findViewById(R.id.cxtext08_3);
        switch08_3 = (Switch) view.findViewById(R.id.switch08_3);
        imageView=view.findViewById(R.id.img8);
       // animationDrawable= (AnimationDrawable) imageView.getBackground();
       // animationDrawable.start();

        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        String dt=simpleDateFormat.format(date);
        text08_1.setText(dt);
        if (date.getDate()%2==0){
            text08_2.setText("双号出行车辆：2");
            switch08_1.setEnabled(false);
            switch08_1.setChecked(false);
            switch08_2.setEnabled(true);
            switch08_2.setChecked(true);
            switch08_3.setEnabled(false);
            switch08_3.setChecked(false);
            cxtext08_1.setText("停");
            cxtext08_2.setText("开");
            cxtext08_3.setText("停");
        }else {
            text08_2.setText("单号出行车辆：1、3");
            switch08_1.setEnabled(true);
            switch08_1.setChecked(true);
            switch08_2.setEnabled(false);
            switch08_2.setChecked(false);
            switch08_3.setEnabled(true);
            switch08_3.setChecked(true);
            cxtext08_1.setText("开");
            cxtext08_2.setText("停");
            cxtext08_3.setText("开");
        }

        switch08_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cxtext08_1.setText("开");
                }else {
                    cxtext08_1.setText("停");
                }
            }
        });
        switch08_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cxtext08_2.setText("开");
                }else {
                    cxtext08_2.setText("停");
                }
            }
        });
        switch08_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cxtext08_3.setText("开");
                }else {
                    cxtext08_3.setText("停");
                }
            }
        });

        text08_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        text08_1.setText(i+"年"+i1+"月"+i2+"日");

                        if (i2%2==0){
                            text08_2.setText("双号出行车辆：2");
                            switch08_1.setEnabled(false);
                            switch08_1.setChecked(false);
                            switch08_2.setEnabled(true);
                            switch08_2.setChecked(true);
                            switch08_3.setEnabled(false);
                            switch08_3.setChecked(false);
                            cxtext08_1.setText("停");
                            cxtext08_2.setText("开");
                            cxtext08_3.setText("停");
                        }else {
                            text08_2.setText("单号出行车辆：1、3");
                            switch08_1.setEnabled(true);
                            switch08_1.setChecked(true);
                            switch08_2.setEnabled(false);
                            switch08_2.setChecked(false);
                            switch08_3.setEnabled(true);
                            switch08_3.setChecked(true);
                            cxtext08_1.setText("开");
                            cxtext08_2.setText("停");
                            cxtext08_3.setText("开");
                        }
                    }
                },date.getYear(),date.getMonth(),date.getDate());
                datePickerDialog.show();

            }
        });

    }
}
