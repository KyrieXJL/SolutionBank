package com.lenovo.smarttraffic;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Entity.Car06_5;
import com.lenovo.smarttraffic.Entity.Car06_6;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment06_6 extends Fragment {


    private PieChart pchart06_6;
    private HttpHelper httpHelper;
    private Gson gson;


    public Fragment06_6() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment06_6, container, false);
        initView(view);
        setLegend();
        setXAxis();
        setYAxis();
        setData();
        return view;
    }

    private void setData() {

        httpHelper.PostJosn("T201737_2", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car06_6> car06_6s=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car06_6>>(){}.getType());
                String[] data=new String[car06_6s.size()];
                int count=0;
                for (Car06_6 car06_6:car06_6s){
                    data[count]=car06_6.getCarno();
                    count++;
                }
                int a=0;
                int[] b=new int[car06_6s.size()];
                for (int i = 0; i < car06_6s.size(); i++) {
                    for (int j = i+1; j <car06_6s.size() ; j++) {
                        if (data[j].equals(data[i])){
                            data[j]="null"+j;
                            b[i]++;
                            a++;
                        }
                    }
                }
                int c=0;
                for (int i = 0; i < car06_6s.size(); i++) {
                    if (b[i]>=1){
                        c++;
                    }
                }

                int zong=c+a;
                List<PieEntry> entries=new ArrayList<>();
                entries.add(new PieEntry(zong,"有重复违章"));
                entries.add(new PieEntry(car06_6s.size()-zong,"无重复违章"));

                /*List<Car06_6> car06_6s=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car06_6>>(){}.getType());
                String[] data=new String[car06_6s.size()];
                int a=0;
                for (Car06_6 car06_6:car06_6s){
                    data[a]=car06_6.getCarno();
                    a++;
                }

                int[] b=new int[car06_6s.size()];
                int c=0;
                for (int i = 0; i < car06_6s.size(); i++) {
                    for (int j = i+1; j < car06_6s.size(); j++) {
                        if (data[j].equals(data[i])){
                            data[j]="null"+j;
                            b[i]++;
                            c++;

                        }

                    }

                }
                int d=-0;
                for (int i = 0; i < car06_6s.size(); i++) {
                    if (b[i]>0){
                        d++;
                    }

                }
                int zong=c+d;
                List<PieEntry> entries=new ArrayList<>();
                entries.add(new PieEntry(zong,"有重复违章"));
                entries.add(new PieEntry(car06_6s.size()-zong,"无重复违章"));*/
                List<Integer> colors=new ArrayList<>();
                colors.add(Color.RED);
                colors.add(Color.BLUE);

                PieDataSet pieDataSet=new PieDataSet(entries,"");
                pieDataSet.setColors(colors);
                pieDataSet.setValueLinePart1Length(1.2f);
                pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                pieDataSet.setValueTextSize(30);
                pieDataSet.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return super.getFormattedValue(value)+"%";
                    }
                });

                PieData pieData=new PieData(pieDataSet);
                pchart06_6.setData(pieData);
                pchart06_6.setTransparentCircleRadius(0);
                pchart06_6.setHoleRadius(0);
                pchart06_6.invalidate();
            }
        });
    }

    private void setYAxis() {

    }

    private void setXAxis() {

    }

    private void setLegend() {

        Legend legend=pchart06_6.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setTextSize(20);
    }

    private void initView(View view) {
        pchart06_6 = (PieChart) view.findViewById(R.id.pchart06_6);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
    }
}
