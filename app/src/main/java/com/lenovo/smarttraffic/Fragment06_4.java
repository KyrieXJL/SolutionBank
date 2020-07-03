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
import com.lenovo.smarttraffic.Entity.Car06_2;
import com.lenovo.smarttraffic.Entity.Car06_4;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment06_4 extends Fragment {


    private PieChart pchart06_4;
    private HttpHelper httpHelper;
    private Gson gson;

    public Fragment06_4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment06_4, container, false);
        initView(view);
        setLegend();
        setXAxis();
        setYAxis();
        setData();
        return view;
    }

    private void setData() {
        httpHelper.PostJosn("T201737_1", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car06_4> car06_4s=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car06_4>>(){}.getType());
                int count=0;
                for (Car06_4 car06_4:car06_4s){
                    String a=car06_4.getStatus();
                    if (a.equals("有违章")){
                        count++;
                    }
                }
                List<PieEntry> entries=new ArrayList<>();
                entries.add(new PieEntry(count,"有违章"));
                entries.add(new PieEntry(car06_4s.size()-count,"无违章"));

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
                pchart06_4.setHoleRadius(0);
                pchart06_4.setData(pieData);
                pchart06_4.setTransparentCircleRadius(0);
                pchart06_4.invalidate();//进入显示图形

            }
        });
    }

    private void setYAxis() {

    }

    private void setXAxis() {

    }

    private void setLegend() {

        Legend legend=pchart06_4.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setTextSize(20);
    }

    private void initView(View view) {
        pchart06_4 = (PieChart) view.findViewById(R.id.pchart06_4);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
    }
}
