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
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment06_5 extends Fragment {


    private PieChart pchart06_5;
    private HttpHelper httpHelper;
    private Gson gson;

    public Fragment06_5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment06_5, container, false);
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
                List<Car06_5> car06_5s=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car06_5>>(){}.getType());
               // LinkedList<Car06_5> linkedList=new LinkedList<>();
               // Set<Car06_5> set=new LinkedHashSet<>();

                TreeSet<Car06_5> treeSet=new TreeSet<>();//需要在类中设置排序的方式compareTo（）方法  this.carno.compareTo(s.carno);
                for (Car06_5 car06_5:car06_5s){
                   // linkedList.add(new Car06_5(car06_5.getCdate(),car06_5.getCarno()));
                  // set.add(new Car06_5(car06_5.getCarno()));
                   treeSet.add(new Car06_5(car06_5.getCdate(),car06_5.getCarno()));
                }

                //int count=car06_5s.size()-linkedList.size();
               // int count=car06_5s.size()-set.size();
                int count=car06_5s.size()-treeSet.size();
                List<PieEntry> pieEntries=new ArrayList<>();
                pieEntries.add(new PieEntry(count,"有重复违章"));
                pieEntries.add(new PieEntry(treeSet.size(),"无重复违章"));
                List<Integer> colors=new ArrayList<>();
                colors.add(Color.RED);
                colors.add(Color.BLUE);

                PieDataSet pieDataSet=new PieDataSet(pieEntries,"");
                pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                pieDataSet.setValueLinePart1Length(1.2f);
                pieDataSet.setColors(colors);
                pieDataSet.setValueTextSize(20);
                pieDataSet.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return super.getFormattedValue(value)+"%";
                    }
                });

                PieData pieData=new PieData(pieDataSet);
                pchart06_5.invalidate();
                pchart06_5.setHoleRadius(0);
                pchart06_5.setTransparentCircleRadius(0);
                pchart06_5.setData(pieData);

            }
        });
    }

    private void setYAxis() {

    }

    private void setXAxis() {

    }

    private void setLegend() {
        Legend legend=pchart06_5.getLegend();
        legend.setTextSize(20);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);

    }

    private void initView(View view) {
        pchart06_5 = (PieChart) view.findViewById(R.id.pchart06_5);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
    }
}
