package com.lenovo.smarttraffic;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.lenovo.smarttraffic.Helper.HttpHelper;
import com.lenovo.smarttraffic.Helper.TimerHelper;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment13 extends Fragment {


    private ImageView image13;
    private TextView text13_1;
    private TextView text13_2;
    private TextView text13_3;
    private TextView text13_4;
    private TextView text13_5;
    private LinearLayout linear13;
    private HttpHelper httpHelper;
    private TimerHelper timerHelper;
    private Timer timer;

    public Fragment13() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment13, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        image13 = (ImageView) view.findViewById(R.id.image13);
        text13_1 = (TextView) view.findViewById(R.id.text13_1);
        text13_2 = (TextView) view.findViewById(R.id.text13_2);
        text13_3 = (TextView) view.findViewById(R.id.text13_3);
        text13_4 = (TextView) view.findViewById(R.id.text13_4);
        text13_5 = (TextView) view.findViewById(R.id.text13_5);
        linear13 = (LinearLayout) view.findViewById(R.id.linear13);
        httpHelper=HttpHelper.getInstance(getContext());
        timerHelper=TimerHelper.getInstance(getContext());
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String dt=simpleDateFormat.format(date);
        if (date.getDay()==1){
            text13_1.setText(dt+"\n"+"星期一");
        }else if (date.getDay()==2){
            text13_1.setText(dt+"\n"+"星期二");
        }else if (date.getDay()==3){
            text13_1.setText(dt+"\n"+"星期三");
        }else if (date.getDay()==4){
            text13_1.setText(dt+"\n"+"星期四");
        }else if (date.getDay()==5){
            text13_1.setText(dt+"\n"+"星期五");
        }else if (date.getDay()==6){
            text13_1.setText(dt+"\n"+"星期六");
        }else if (date.getDay()==0){
            text13_1.setText(dt+"\n"+"星期日");
        }

        send1();
        send2();
        send3();
        image13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send1();
                send2();
                send3();
            }
        });


        send4();
    }

    private void send4() {
        timer=timerHelper.starTimer("P5_6", "{\"way\":0}", 3, new TimerHelper.UpdateUI() {
            @Override
            public void update(JSONObject object) {
                int a=object.optJSONObject("serverinfo").optInt("status");
                if (a==1){
                    text13_5.setText("畅通");
                    linear13.setBackgroundColor(Color.parseColor("#6ab82e"));
                }else if (a==2){
                    text13_5.setText("缓行");
                    linear13.setBackgroundColor(Color.parseColor("#ece93a"));
                }else if (a==3){
                    text13_5.setText("一般拥堵");
                    linear13.setBackgroundColor(Color.parseColor("#f49b25"));
                }else if (a==4){
                    text13_5.setText("中度拥堵");
                    linear13.setBackgroundColor(Color.parseColor("#e33532"));
                }else if (a==5){
                    text13_5.setText("严重拥堵");
                    linear13.setBackgroundColor(Color.parseColor("#b01e23"));
                }
            }
        });
    }

    private void send3() {
        httpHelper.PostJosn("P14_5", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String a=jsonObject.optJSONObject("serverinfo").optString("pm25");
                text13_4.setText("PM2.5:"+a);
            }
        });
    }

    private void send2() {
        httpHelper.PostJosn("P14_6", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String a=jsonObject.optJSONObject("serverinfo").optString("humity");
                text13_3.setText("相对湿度："+a+"%");
            }
        });
    }

    private void send1() {
        httpHelper.PostJosn("P14_3", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String a=jsonObject.optJSONObject("serverinfo").optString("tem");
                text13_2.setText("温度："+a+"℃");
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
