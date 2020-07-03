package com.lenovo.smarttraffic;


import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Entity.Car06_1;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment06_1 extends Fragment {


    private LineChart lchart06_1;
    private HttpHelper httpHelper;
    private Gson gson;
    NotificationManager notificationManager;


    public Fragment06_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment06_1, container, false);
        initView(view);
        setLegend();
        setXAxis();
        setYAxis();
        setData();
        return view;
    }

    private void setData() {
        httpHelper.PostJosn("P29_1", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                List<Car06_1> car06_1s=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car06_1>>(){}.getType());
                List<Entry> entries=new ArrayList<>();
                List<Integer> color=new ArrayList<>();
                int i=0;
                for (Car06_1 car06_1:car06_1s){
                    entries.add(new Entry(i,car06_1.getPm25()));
                    if (car06_1.getPm25()>200){
                        color.add(Color.RED);
                        notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                        NotificationCompat.Builder builder=new NotificationCompat.Builder(getContext());
                        builder.setSmallIcon(R.drawable.car04).setContentTitle("PM2.5报警").setContentText("当前PM2.5值为："+car06_1.getPm25());
                        notificationManager.notify(i,builder.build());

                    }else {
                        color.add(Color.GREEN);
                    }
                    i++;
                }
                Description description=new Description();
                description.setEnabled(false);
                lchart06_1.setDescription(description);

                LineDataSet lineDataSet=new LineDataSet(entries,"");
                lineDataSet.setCircleColors(color);
                lineDataSet.setColor(Color.BLACK);
                lineDataSet.setDrawCircleHole(false);
                lineDataSet.setCircleRadius(10);


                LineData lineData=new LineData(lineDataSet);
                lchart06_1.animateX(18000);
                lchart06_1.setData(lineData);
                lineData.setDrawValues(false);



            }
        });
    }

    private void setYAxis() {

        YAxis yAxis=lchart06_1.getAxisLeft();
        yAxis.setAxisMaximum(300);
        yAxis.setAxisMinimum(0);
        yAxis.setTextSize(20);
        lchart06_1.getAxisRight().setEnabled(false);

}

    private void setXAxis() {

        XAxis xAxis=lchart06_1.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);//x轴值间距
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(30);
        String[] data=new String[]{"1","3","6","9","12","15"};
        xAxis.setLabelCount(data.length);
       // xAxis.setLabelRotationAngle(10);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return data[(int) value];
            }
        });

    }

    private void setLegend() {

        Legend legend=lchart06_1.getLegend();
        legend.setEnabled(false);
    }

    private void initView(View view) {
        lchart06_1 = (LineChart) view.findViewById(R.id.lchart06_1);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
        lchart06_1.setExtraOffsets(0,0,0,30);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (notificationManager!=null){
            notificationManager.cancel(0);
            notificationManager.cancel(1);
            notificationManager.cancel(2);
            notificationManager.cancel(3);
            notificationManager.cancel(4);
            notificationManager.cancel(5);

        }

    }
}
