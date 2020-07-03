package com.lenovo.smarttraffic;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovo.smarttraffic.Helper.HttpHelper;
import com.lenovo.smarttraffic.Helper.TimerHelper;

import org.json.JSONObject;

import java.sql.Time;
import java.util.Timer;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment25 extends Fragment {


    private TextView text25_1;
    private TextView text25_2;
    private TextView text25_3;
    private TextView text23_4;
    private TextView text23_5;
    private TextView text23_6;
    private LinearLayout linear23_1;
    private LinearLayout linear23_2;
    private LinearLayout linear23_3;
    private HttpHelper httpHelper;
    private TimerHelper timerHelper;
    private Timer timer;

    public Fragment25() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment25, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text25_1 = (TextView) view.findViewById(R.id.text25_1);
        text25_2 = (TextView) view.findViewById(R.id.text25_2);
        text25_3 = (TextView) view.findViewById(R.id.text25_3);
        text23_4 = (TextView) view.findViewById(R.id.text23_4);
        text23_5 = (TextView) view.findViewById(R.id.text23_5);
        text23_6 = (TextView) view.findViewById(R.id.text23_6);
        linear23_1 = (LinearLayout) view.findViewById(R.id.linear23_1);
        linear23_2 = (LinearLayout) view.findViewById(R.id.linear23_2);
        linear23_3 = (LinearLayout) view.findViewById(R.id.linear23_3);
        httpHelper=HttpHelper.getInstance(getContext());
        timerHelper=TimerHelper.getInstance(getContext());
        send1();
        send2();
        send3();
        send4();
        send5();
        send6();
    }
    private void send6() {
        timer=timerHelper.starTimer("P5_6", "{\"way\":0}", 3, new TimerHelper.UpdateUI() {
            @Override
            public void update(JSONObject object) {
                int a=object.optJSONObject("serverinfo").optInt("status");
                if (a==1){
                    text23_6.setText("3号道路：通畅");
                    linear23_3.setBackgroundColor(Color.parseColor("#0ebd12"));

                }else if (a==2){
                    text23_6.setText("3号道路：较通畅");
                    linear23_3.setBackgroundColor(Color.parseColor("#98ed1f"));
                }else if (a==3){
                    text23_6.setText("3号道路：拥挤");
                    linear23_3.setBackgroundColor(Color.parseColor("#ffff01"));
                }else if (a==4){
                    text23_6.setText("3号道路：堵塞");
                    linear23_3.setBackgroundColor(Color.parseColor("#ff0103"));
                }else if (a==5){
                    text23_6.setText("3号道路：爆表");
                    linear23_3.setBackgroundColor(Color.parseColor("#4c060e"));
                }
            }
        });
    }
    private void send5() {
        timer=timerHelper.starTimer("P5_6", "{\"way\":0}", 3, new TimerHelper.UpdateUI() {
            @Override
            public void update(JSONObject object) {
                int a=object.optJSONObject("serverinfo").optInt("status");
                if (a==1){
                    text23_5.setText("2号道路：通畅");
                    linear23_2.setBackgroundColor(Color.parseColor("#0ebd12"));

                }else if (a==2){
                    text23_5.setText("2号道路：较通畅");
                    linear23_2.setBackgroundColor(Color.parseColor("#98ed1f"));
                }else if (a==3){
                    text23_5.setText("2号道路：拥挤");
                    linear23_2.setBackgroundColor(Color.parseColor("#ffff01"));
                }else if (a==4){
                    text23_5.setText("2号道路：堵塞");
                    linear23_2.setBackgroundColor(Color.parseColor("#ff0103"));
                }else if (a==5){
                    text23_5.setText("2号道路：爆表");
                    linear23_2.setBackgroundColor(Color.parseColor("#4c060e"));
                }
            }
        });
    }

    private void send4() {
        timer=timerHelper.starTimer("P5_6", "{\"way\":0}", 3, new TimerHelper.UpdateUI() {
            @Override
            public void update(JSONObject object) {
                int a=object.optJSONObject("serverinfo").optInt("status");
                if (a==1){
                    text23_4.setText("1号道路：通畅");
                    linear23_1.setBackgroundColor(Color.parseColor("#0ebd12"));

                }else if (a==2){
                    text23_4.setText("1号道路：较通畅");
                    linear23_1.setBackgroundColor(Color.parseColor("#98ed1f"));
                }else if (a==3){
                    text23_4.setText("1号道路：拥挤");
                    linear23_1.setBackgroundColor(Color.parseColor("#ffff01"));
                }else if (a==4){
                    text23_4.setText("1号道路：堵塞");
                    linear23_1.setBackgroundColor(Color.parseColor("#ff0103"));
                }else if (a==5){
                    text23_4.setText("1号道路：爆表");
                    linear23_1.setBackgroundColor(Color.parseColor("#4c060e"));
                }
            }
        });
    }

    private void send3() {
        timer=timerHelper.starTimer("P5_2", "{\"way\":0}", 3, new TimerHelper.UpdateUI() {
            @Override
            public void update(JSONObject object) {
                String a=object.optJSONObject("serverinfo").optString("hum");
                text25_3.setText(a);
            }
        });
    }

    private void send2() {
        timer=timerHelper.starTimer("P5_1", "{\"way\":0}", 3, new TimerHelper.UpdateUI() {
            @Override
            public void update(JSONObject object) {
                String a=object.optJSONObject("serverinfo").optString("tem");
                text25_2.setText(a);
            }
        });
    }

    private void send1() {
        timer=timerHelper.starTimer("P5_5", "{\"way\":0}", 3, new TimerHelper.UpdateUI() {
            @Override
            public void update(JSONObject object) {
                String a=object.optJSONObject("serverinfo").optString("pm25");
                text25_1.setText(a);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
        }
    }
}
