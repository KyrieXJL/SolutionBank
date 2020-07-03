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
import com.lenovo.smarttraffic.Entity.Car06_7;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment06_7 extends Fragment {


    private HorizontalBarChart hbchart06_7;
    private HttpHelper httpHelper;
    private Gson gson;
    String[] b;

    public Fragment06_7() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment06_7, container, false);
        initView(view);
        setLegend();
        setXAxis();
        setYAxis();
        setData();
        return view;
    }

    private void setData() {
        httpHelper.PostJosn("T201737_7", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car06_7> car06_7s=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car06_7>>(){}.getType());
                List<String> list=new ArrayList<>();
                for (Car06_7 car06_7:car06_7s){
                    list.add(car06_7.getType());
                }

                Map<String,Integer> map=new HashMap<>();
                for (String type:list){
                    Integer i=1;
                    if (map.get(type)!=null){
                        i=map.get(type)+1;
                    }
                    map.put(type,i);
                }

                List<Map.Entry<String,Integer>> list1=new ArrayList<>(map.entrySet());
                Collections.sort(list1, new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> stringIntegerEntry, Map.Entry<String, Integer> t1) {
                        if (stringIntegerEntry.getValue()>t1.getValue()){
                            return -1;
                        }else if (stringIntegerEntry.getValue()==t1.getValue()){
                            return 0;
                        }else {
                            return 1;
                        }
                    }
                });
                Log.d("data",list1.toString());
                int[] a=new int[list1.size()];
                b=new String[list1.size()];
                int j=0;
                int e=9;
                for (Map.Entry<String,Integer> map1:list1){
                    a[j]=map1.getValue();
                    if (e>=0){
                        b[e]=map1.getKey();
                        e--;
                    }

                    j++;
                }

                List<BarEntry> entries=new ArrayList<>();
                entries.add(new BarEntry(9,(float)a[0]/car06_7s.size()*100));
                entries.add(new BarEntry(8,(float)a[1]/car06_7s.size()*100));
                entries.add(new BarEntry(7,(float)a[2]/car06_7s.size()*100));
                entries.add(new BarEntry(6,(float)a[3]/car06_7s.size()*100));
                entries.add(new BarEntry(5,(float)a[4]/car06_7s.size()*100));
                entries.add(new BarEntry(4,(float)a[5]/car06_7s.size()*100));
                entries.add(new BarEntry(3,(float)a[6]/car06_7s.size()*100));
                entries.add(new BarEntry(2,(float)a[7]/car06_7s.size()*100));
                entries.add(new BarEntry(1,(float)a[8]/car06_7s.size()*100));
                entries.add(new BarEntry(0,(float)a[9]/car06_7s.size()*100));

                List<Integer> colors=new ArrayList<>();
                colors.add(Color.BLACK);
                colors.add(Color.BLUE);
                colors.add(Color.GREEN);
                colors.add(Color.RED);
                colors.add(Color.YELLOW);
                colors.add(Color.BLACK);
                colors.add(Color.BLUE);
                colors.add(Color.GREEN);
                colors.add(Color.RED);
                colors.add(Color.YELLOW);

                BarDataSet barDataSet=new BarDataSet(entries,"");
                barDataSet.setColors(colors);
                barDataSet.setDrawValues(true);
                barDataSet.setValueTextSize(20);

                BarData barData=new BarData(barDataSet);
                barData.setValueTextSize(20);
                barData.setBarWidth(0.6f);
                barData.setDrawValues(true);
                barData.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return value+"%";
                    }
                });
                hbchart06_7.setData(barData);
                hbchart06_7.notifyDataSetChanged();

            }
        });
    }

    private void setYAxis() {
        YAxis yAxis=hbchart06_7.getAxisRight();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setTextSize(20);
        yAxis.setDrawGridLines(true);
        yAxis.setGranularity(1f);
        yAxis.setDrawAxisLine(true);
        yAxis.setDrawLabels(true);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value+"%";
            }
        });
        hbchart06_7.getAxisLeft().setEnabled(false);

    }

    private void setXAxis() {

        XAxis xAxis=hbchart06_7.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setTextSize(20);
        xAxis.setGranularity(1f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawLabels(true);
        xAxis.setLabelCount(10,false);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return b[(int) value];
            }
        });

    }

    private void setLegend() {
        Legend legend=hbchart06_7.getLegend();
        legend.setFormSize(1);

    }

    private void initView(View view) {
        hbchart06_7 = (HorizontalBarChart) view.findViewById(R.id.hbchart06_7);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();

    }
}
