package com.lenovo.smarttraffic;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Entity.Car06_8;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment06_8 extends Fragment {


    private HorizontalBarChart hbchart06_8;
    private HttpHelper httpHelper;
    private Gson gson;


    public Fragment06_8() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment06_8, container, false);
        initView(view);
        setLegend();
        setXAxis();
        setYAxis();
        steData();
        return view;
    }

    private void steData() {
        httpHelper.PostJosn("T201737_3", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car06_8> car06_8s=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car06_8>>(){}.getType());
                List<String> list=new ArrayList<>();
                for (Car06_8 car06_8:car06_8s){
                    list.add(car06_8.getCarno());
                }

                Map<String,Integer> map=new HashMap<>();
                for (String carno:list){
                    Integer i=1;
                    if (map.get(carno)!=null){
                        i=map.get(carno)+1;
                    }
                    map.put(carno,i);
                }
                Log.d("data",map.toString());

                int[] a=new int[map.size()+1];
                for (int i = 1; i <map.size()+1 ; i++) {
                    for (String map1:map.keySet()){
                        if (map.get(map1)==i){
                            a[i]++;
                        }
                    }
                }

                int zong1=0;
                int zong2=0;
                int zong3=0;
                for (int i = 1; i < map.size()+1; i++) {
                    if (i>=1&i<=2){
                        zong1=zong1+a[i]*i;
                    }else if (i>=3&&i<=4){
                        zong2=zong2+a[i]*i;
                    }else if (i>=5){
                        zong3=zong3+a[i]*i;
                    }

                }

                List<BarEntry> entries=new ArrayList<>();
                entries.add(new BarEntry(0,(float)zong1/car06_8s.size()*100));
                entries.add(new BarEntry(1,(float)zong2/car06_8s.size()*100));
                entries.add(new BarEntry(2,(float)zong3/car06_8s.size()*100));

                List<Integer> colors=new ArrayList<>();
                colors.add(Color.GREEN);
                colors.add(Color.BLUE);
                colors.add(Color.RED);

                BarDataSet barDataSet=new BarDataSet(entries,"");
                barDataSet.setValueTextSize(20);
                barDataSet.setDrawValues(true);
                barDataSet.setColors(colors);
                barDataSet.setDrawValues(true);
                barDataSet.setBarBorderWidth(1f);

                BarData barData=new BarData(barDataSet);
                barData.setBarWidth(0.4f);
                barData.setDrawValues(true);
                barData.setValueTextSize(20);
                barData.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return value+"%";
                    }
                });
                hbchart06_8.setData(barData);
                hbchart06_8.notifyDataSetChanged();


            }
        });
    }

    private void setYAxis() {

        YAxis yAxis=hbchart06_8.getAxisRight();
        yAxis.setDrawLabels(true);
        yAxis.setGranularity(1f);
        yAxis.setDrawGridLines(true);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisMinimum(0f);
        yAxis.setTextSize(20);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value+"%";
            }
        });
        YAxis yAxis1=hbchart06_8.getAxisLeft();
        yAxis1.setAxisMinimum(0f);
        yAxis1.setGranularity(1f);
        yAxis1.setEnabled(false);
       // hbchart06_8.getAxisLeft().setEnabled(false);
    }

    private void setXAxis() {

        XAxis xAxis=hbchart06_8.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setTextSize(20);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        String[] data=new String[]{"1——2条违章","3——5条违章","5条以上违章"};
        xAxis.setLabelCount(data.length,false);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return data[(int) value];
            }
        });
    }

    private void setLegend() {
        Legend legend=hbchart06_8.getLegend();
        legend.setFormSize(1);

    }

    private void initView(View view) {
        hbchart06_8 = (HorizontalBarChart) view.findViewById(R.id.hbchart06_8);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();

    }
}
