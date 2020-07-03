package com.lenovo.smarttraffic;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lenovo.smarttraffic.Helper.TimerHelper;

import org.json.JSONObject;

import java.util.Timer;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment27 extends Fragment {


    private TextView text27_1;
    private TextView text27_2;
    private TextView text27_3;
    private TextView text27_4;
    private TextView text27_5;
    private TextView text27_6;
    private TextView text27_7;
    private TimerHelper timerHelper;
    private Timer timer;

    public Fragment27() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment27, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text27_1 = (TextView) view.findViewById(R.id.text27_1);
        text27_2 = (TextView) view.findViewById(R.id.text27_2);
        text27_3 = (TextView) view.findViewById(R.id.text27_3);
        text27_4 = (TextView) view.findViewById(R.id.text27_4);
        text27_5 = (TextView) view.findViewById(R.id.text27_5);
        text27_6 = (TextView) view.findViewById(R.id.text27_6);
        text27_7 = (TextView) view.findViewById(R.id.text27_7);
        timerHelper=TimerHelper.getInstance(getContext());
        send1();
        send2();
        send3();
        send4();

    }

    private void send4() {
        timer=timerHelper.starTimer("P5_4", "{\"way\":0}", 3, new TimerHelper.UpdateUI() {
            @Override
            public void update(JSONObject object) {
                int a=object.optJSONObject("serverinfo").optInt("light");
                if (a<1000){

                    text27_4.setText("非常弱");
                    text27_4.setTextColor(Color.BLACK);
                    text27_5.setText("您无需担心紫外线");
                }else if (a>=1000&&a<2000){
                    text27_4.setText("弱");
                    text27_4.setTextColor(Color.BLACK);
                    text27_5.setText("外出适当涂抹低倍数防晒霜");
                }else if (a>=2000){
                    text27_4.setText("强");
                    text27_4.setTextColor(Color.RED);
                    text27_5.setText("外出需要涂抹中倍数防晒霜");
                }
            }
        });
    }

    private void send3() {
        timer=timerHelper.starTimer("P5_2", "{\"way\":0}", 3, new TimerHelper.UpdateUI() {
            @Override
            public void update(JSONObject object) {
                String a=object.optJSONObject("serverinfo").optString("hum");
                text27_3.setText(a);
            }
        });
    }

    private void send2() {
        timer=timerHelper.starTimer("P5_1", "{\"way\":0}", 3, new TimerHelper.UpdateUI() {
            @Override
            public void update(JSONObject object) {
                int a=object.optJSONObject("serverinfo").optInt("tem");
                text27_2.setText(a+"");

            }
        });
    }

    private void send1() {
        timer=timerHelper.starTimer("P5_5", "{\"way\":0}", 3, new TimerHelper.UpdateUI() {
            @Override
            public void update(JSONObject object) {
                int a=object.optJSONObject("serverinfo").optInt("pm25");
                text27_1.setText(a+"");
                if (a>=0&&a<100){
                    text27_6.setText("良好");
                    text27_6.setTextColor(Color.BLACK);
                    text27_7.setText("气象条件会对晨练影响不大");
                }else if (a>=100&&a<200){
                    text27_6.setText("较轻");
                    text27_6.setTextColor(Color.BLACK);
                    text27_7.setText("受天气影响，较不宜晨练");
                }else if (a>=200&&a<300){
                    text27_6.setText("重度");
                    text27_6.setTextColor(Color.RED);
                    text27_7.setText("减少外出，出行注意戴口罩");
                }else if (a>=300){
                    text27_6.setText("爆表");
                    text27_6.setTextColor(Color.RED);
                    text27_7.setText("停止一切外出活动");
                }
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
