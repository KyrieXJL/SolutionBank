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

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment36 extends Fragment {


    private ImageView image36;
    private ImageView image36_1;
    private TextView text36_1;
    private TextView text36_2;
    private TextView ybtext36_1_1;
    private ImageView ybimage36_1;
    private TextView ybtext36_1_2;
    private TextView ybtext36_1_3;
    private LinearLayout yblinear36_1;
    private TextView ybtext36_2_1;
    private ImageView ybimage36_2;
    private TextView ybtext36_2_2;
    private TextView ybtext36_2_3;
    private LinearLayout yblinear36_2;
    private TextView ybtext36_3_1;
    private ImageView ybimage36_3;
    private TextView ybtext36_3_2;
    private TextView ybtext36_3_3;
    private LinearLayout yblinear36_3;
    private TextView ybtext36_4_1;
    private ImageView ybimage36_4;
    private TextView ybtext36_4_2;
    private TextView ybtext36_4_3;
    private LinearLayout yblinear36_4;
    private TextView ybtext36_5_1;
    private ImageView ybimage36_5;
    private TextView ybtext36_5_2;
    private TextView ybtext36_5_3;
    private LinearLayout yblinear36_5;
    private HttpHelper httpHelper;

    private Date date;
    private String dt;
    public Fragment36() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment36, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        image36 = (ImageView) view.findViewById(R.id.image36);
        image36_1 = (ImageView) view.findViewById(R.id.image36_1);
        text36_1 = (TextView) view.findViewById(R.id.text36_1);
        text36_2 = (TextView) view.findViewById(R.id.text36_2);
        ybtext36_1_1 = (TextView) view.findViewById(R.id.ybtext36_1_1);
        ybimage36_1 = (ImageView) view.findViewById(R.id.ybimage36_1);
        ybtext36_1_2 = (TextView) view.findViewById(R.id.ybtext36_1_2);
        ybtext36_1_3 = (TextView) view.findViewById(R.id.ybtext36_1_3);
        yblinear36_1 = (LinearLayout) view.findViewById(R.id.yblinear36_1);
        ybtext36_2_1 = (TextView) view.findViewById(R.id.ybtext36_2_1);
        ybimage36_2 = (ImageView) view.findViewById(R.id.ybimage36_2);
        ybtext36_2_2 = (TextView) view.findViewById(R.id.ybtext36_2_2);
        ybtext36_2_3 = (TextView) view.findViewById(R.id.ybtext36_2_3);
        yblinear36_2 = (LinearLayout) view.findViewById(R.id.yblinear36_2);
        ybtext36_3_1 = (TextView) view.findViewById(R.id.ybtext36_3_1);
        ybimage36_3 = (ImageView) view.findViewById(R.id.ybimage36_3);
        ybtext36_3_2 = (TextView) view.findViewById(R.id.ybtext36_3_2);
        ybtext36_3_3 = (TextView) view.findViewById(R.id.ybtext36_3_3);
        yblinear36_3 = (LinearLayout) view.findViewById(R.id.yblinear36_3);
        ybtext36_4_1 = (TextView) view.findViewById(R.id.ybtext36_4_1);
        ybimage36_4 = (ImageView) view.findViewById(R.id.ybimage36_4);
        ybtext36_4_2 = (TextView) view.findViewById(R.id.ybtext36_4_2);
        ybtext36_4_3 = (TextView) view.findViewById(R.id.ybtext36_4_3);
        yblinear36_4 = (LinearLayout) view.findViewById(R.id.yblinear36_4);
        ybtext36_5_1 = (TextView) view.findViewById(R.id.ybtext36_5_1);
        ybimage36_5 = (ImageView) view.findViewById(R.id.ybimage36_5);
        ybtext36_5_2 = (TextView) view.findViewById(R.id.ybtext36_5_2);
        ybtext36_5_3 = (TextView) view.findViewById(R.id.ybtext36_5_3);
        yblinear36_5 = (LinearLayout) view.findViewById(R.id.yblinear36_5);
        httpHelper=HttpHelper.getInstance(getContext());
        date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd ");
        dt=simpleDateFormat.format(date);
        if (date.getDay()==1){
            text36_1.setText(dt+"\t周一");
        }else if (date.getDay()==2){
            text36_1.setText(dt+"\t周二");
        }else if (date.getDay()==3){
            text36_1.setText(dt+"\t周三");
        }else if (date.getDay()==4){
            text36_1.setText(dt+"\t周四");
        }else if (date.getDay()==5){
            text36_1.setText(dt+"\t周五");
        }else if (date.getDay()==6){
            text36_1.setText(dt+"\t周六");
        }else if (date.getDay()==0){
            text36_1.setText(dt+"\t周日");
        }

        send1();
        send2();
        send3();
        send4();
        send5();
        image36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send1();
                send2();
                send3();
                send4();
                send5();
            }
        });
    }
    private void send5() {
        httpHelper.PostJosn("T36_1", "{\"time\":\"\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String high=jsonObject.optJSONObject("serverinfo").optString("high");
                String yun=jsonObject.optJSONObject("serverinfo").optString("yun");
                String low=jsonObject.optJSONObject("serverinfo").optString("low");
                //SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd");
                Calendar calendar=Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH,4);
             //   Date dt=calendar.getTime();


                if (date.getDay()==1){
                    ybtext36_5_1.setText(calendar.get(Calendar.DATE)+"日（周五）");
                }else if (date.getDay()==2){
                    ybtext36_5_1.setText(calendar.get(Calendar.DATE)+"日（周六）");
                }else if (date.getDay()==3){
                    ybtext36_5_1.setText(calendar.get(Calendar.DATE)+"日（周日）");
                }else if (date.getDay()==4){
                    ybtext36_5_1.setText(calendar.get(Calendar.DATE)+"日（周一）");
                }else if (date.getDay()==5){
                    ybtext36_5_1.setText(calendar.get(Calendar.DATE)+"日（周二）");
                }else if (date.getDay()==6){
                    ybtext36_5_1.setText(calendar.get(Calendar.DATE)+"日（周三）");
                }else if (date.getDay()==0){
                    ybtext36_5_1.setText(calendar.get(Calendar.DATE)+"日（周四）");
                }
                ybtext36_5_2.setText(yun);
                if (yun.equals("晴")){
                    ybimage36_5.setImageResource(R.drawable.weather);
                    yblinear36_5.setBackgroundColor(Color.parseColor("#f70cb9ed"));//只能用小写
                }else if (yun.equals("多云转阴")){
                    ybimage36_5.setImageResource(R.drawable.cloudy);
                    yblinear36_5.setBackgroundColor(Color.parseColor("#ff18f2f2"));
                }else if (yun.equals("阴转多云")){
                    ybimage36_5.setImageResource(R.drawable.cloudy_);
                    yblinear36_5.setBackgroundColor(Color.parseColor("#ffd2d0d0"));
                }
                ybtext36_5_3.setText(high+"/"+low+"℃");
            }
        });
    }
    private void send4() {
        httpHelper.PostJosn("T36_1", "{\"time\":\"\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String high=jsonObject.optJSONObject("serverinfo").optString("high");
                String yun=jsonObject.optJSONObject("serverinfo").optString("yun");
                String low=jsonObject.optJSONObject("serverinfo").optString("low");
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd");
                Calendar calendar=Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH,3);
                Date dt=calendar.getTime();

                if (date.getDay()==1){
                    ybtext36_4_1.setText(simpleDateFormat.format(dt)+"日（周四）");
                }else if (date.getDay()==2){
                    ybtext36_4_1.setText(simpleDateFormat.format(dt)+"日（周五）");
                }else if (date.getDay()==3){
                    ybtext36_4_1.setText(simpleDateFormat.format(dt)+"日（周六）");
                }else if (date.getDay()==4){
                    ybtext36_4_1.setText(simpleDateFormat.format(dt)+"日（周日）");
                }else if (date.getDay()==5){
                    ybtext36_4_1.setText(simpleDateFormat.format(dt)+"日（周一）");
                }else if (date.getDay()==6){
                    ybtext36_4_1.setText(simpleDateFormat.format(dt)+"日（周二）");
                }else if (date.getDay()==0){
                    ybtext36_4_1.setText(simpleDateFormat.format(dt)+"日（周三）");
                }
                ybtext36_4_2.setText(yun);
                if (yun.equals("晴")){
                    ybimage36_4.setImageResource(R.drawable.weather);
                    yblinear36_4.setBackgroundColor(Color.parseColor("#f70cb9ed"));
                }else if (yun.equals("多云转阴")){
                    ybimage36_4.setImageResource(R.drawable.cloudy);
                    yblinear36_4.setBackgroundColor(Color.parseColor("#ff18f2f2"));
                }else if (yun.equals("阴转多云")){
                    ybimage36_4.setImageResource(R.drawable.cloudy_);
                    yblinear36_4.setBackgroundColor(Color.parseColor("#ffd2d0d0"));
                }
                ybtext36_4_3.setText(high+"/"+low+"℃");
            }
        });
    }
    private void send3() {
        httpHelper.PostJosn("T36_1", "{\"time\":\"\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String high=jsonObject.optJSONObject("serverinfo").optString("high");
                String yun=jsonObject.optJSONObject("serverinfo").optString("yun");
                String low=jsonObject.optJSONObject("serverinfo").optString("low");
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd");
                Calendar calendar=Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH,2);
                Date dt=calendar.getTime();
                ybtext36_3_1.setText(simpleDateFormat.format(dt)+"日（后天）");
                ybtext36_3_2.setText(yun);
                if (yun.equals("晴")){
                    ybimage36_3.setImageResource(R.drawable.weather);
                    yblinear36_3.setBackgroundColor(Color.parseColor("#f70cb9ed"));
                }else if (yun.equals("多云转阴")){
                    ybimage36_3.setImageResource(R.drawable.cloudy);
                    yblinear36_3.setBackgroundColor(Color.parseColor("#ff18f2f2"));
                }else if (yun.equals("阴转多云")){
                    ybimage36_3.setImageResource(R.drawable.cloudy_);
                    yblinear36_3.setBackgroundColor(Color.parseColor("#ffd2d0d0"));
                }
                ybtext36_3_3.setText(high+"/"+low+"℃");
            }
        });
    }

    private void send2() {
        httpHelper.PostJosn("T36_1", "{\"time\":\"\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String high=jsonObject.optJSONObject("serverinfo").optString("high");
                String yun=jsonObject.optJSONObject("serverinfo").optString("yun");
                String low=jsonObject.optJSONObject("serverinfo").optString("low");
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd");
                Calendar calendar=Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH,1);
                Date dt=calendar.getTime();
                ybtext36_2_1.setText(simpleDateFormat.format(dt)+"日（明天）");
                ybtext36_2_2.setText(yun);
                if (yun.equals("晴")){
                    ybimage36_2.setImageResource(R.drawable.weather);
                    yblinear36_2.setBackgroundColor(Color.parseColor("#f70cb9ed"));
                }else if (yun.equals("多云转阴")){
                    ybimage36_2.setImageResource(R.drawable.cloudy);
                    yblinear36_2.setBackgroundColor(Color.parseColor("#ff18f2f2"));
                }else if (yun.equals("阴转多云")){
                    ybimage36_2.setImageResource(R.drawable.cloudy_);
                    yblinear36_2.setBackgroundColor(Color.parseColor("#ffd2d0d0"));
                }
                ybtext36_2_3.setText(high+"/"+low+"℃");
            }
        });
    }

    private void send1() {

        httpHelper.PostJosn("T36_1", "{\"time\":\"\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String high=jsonObject.optJSONObject("serverinfo").optString("high");
                String yun=jsonObject.optJSONObject("serverinfo").optString("yun");
                String low=jsonObject.optJSONObject("serverinfo").optString("low");
                text36_2.setText(high+"度");
                ybtext36_1_1.setText(date.getDate()+"日"+"（今天）");
                ybtext36_1_2.setText(yun);
                if (yun.equals("晴")){
                    image36_1.setImageResource(R.drawable.weather);
                    ybimage36_1.setImageResource(R.drawable.weather);
                    yblinear36_1.setBackgroundColor(Color.parseColor("#f70cb9ed"));
                }else if (yun.equals("多云转阴")){
                    image36_1.setImageResource(R.drawable.cloudy);
                    ybimage36_1.setImageResource(R.drawable.cloudy);
                    yblinear36_1.setBackgroundColor(Color.parseColor("#ff18f2f2"));
                }else if (yun.equals("阴转多云")){
                    image36_1.setImageResource(R.drawable.cloudy_);
                    ybimage36_1.setImageResource(R.drawable.cloudy_);
                    yblinear36_1.setBackgroundColor(Color.parseColor("#ffd2d0d0"));
                }
                ybtext36_1_3.setText(high+"/"+low+"℃");
            }
        });
    }
}
