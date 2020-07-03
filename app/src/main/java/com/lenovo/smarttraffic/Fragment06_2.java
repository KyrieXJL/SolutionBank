package com.lenovo.smarttraffic;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Entity.Car06_2;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment06_2 extends Fragment {


    private LineChart lchart06_2;
    private HttpHelper httpHelper;

    public Fragment06_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment06_2, container, false);
        initView(view);
        setLegend();
        setXAxis();
        setYAxis();
        setData();
        return view;
    }

    private void setData() {
        Gson gson=new Gson();
        httpHelper.PostJosn("P6_1", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car06_2> car06_2s=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car06_2>>(){}.getType());
                List<Entry> entries=new ArrayList<>();
                int i=0;
                for (Car06_2 car06_2:car06_2s){
                    entries.add(new Entry(i,car06_2.getHum()));
                    i++;
                }
                LineDataSet lineDataSet=new LineDataSet(entries,"");
                lineDataSet.setCircleRadius(10);
                lineDataSet.setDrawCircleHole(false);
                lineDataSet.setCircleColor(Color.BLACK);
                lineDataSet.setColor(Color.BLACK);

                LineData lineData=new LineData(lineDataSet);
                lineData.setDrawValues(false);
                lchart06_2.setData(lineData);
                lchart06_2.animateX(20000);
            }
        });

    }

    private void setYAxis() {
        YAxis yAxis=lchart06_2.getAxisLeft();
        yAxis.setAxisMaximum(32);
        yAxis.setAxisMinimum(14);
        yAxis.setTextSize(15);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value)+"℃";
            }
        });
        lchart06_2.getAxisRight().setEnabled(false);
    }

    private void setXAxis() {

        XAxis xAxis=lchart06_2.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(30);
        xAxis.setLabelRotationAngle(90);//字体旋转显示
        String[] data=new String[]{"55:03","55:06","55:09","55:12","55:15","55:18",
                "55:21","55:24","55:27","55:30","55:33","55:36","55:39"
                ,"55:42","55:45","55:48","55:51","55:54","55:57","56:00"
        };
        xAxis.setLabelCount(data.length);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return data[(int) value];
            }
        });
    }

    private void setLegend() {
        Legend legend=lchart06_2.getLegend();
        legend.setEnabled(false);

    }

    private void initView(View view) {
        lchart06_2 = (LineChart) view.findViewById(R.id.lchart06_2);
        httpHelper=HttpHelper.getInstance(getContext());
    }
}
