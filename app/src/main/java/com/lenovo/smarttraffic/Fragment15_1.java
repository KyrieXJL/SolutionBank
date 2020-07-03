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
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Entity.Car15_1;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment15_1 extends Fragment {


    private PieChart pc15_1;

    public Fragment15_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment15_1, container, false);
        initView(view);
        setLegend();
        setXAxis();
        setYAxis();
        setData();
        return view;
    }

    private void setData() {
        HttpHelper httpHelper=HttpHelper.getInstance(getContext());
        Gson gson=new Gson();
        httpHelper.PostJosn("T201737_1", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car15_1> car15_1s=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car15_1>>(){}.getType());
                int count=0;
                for (Car15_1 car15_1:car15_1s){
                    if (car15_1.getStatus().equals("无违章")){
                        count++;
                    }
                }
                List<PieEntry> pieEntries=new ArrayList<>();
                pieEntries.add(new PieEntry(count,"无违章"));
                pieEntries.add(new PieEntry(car15_1s.size()-count,"有违章"));

                List<Integer> colors=new ArrayList<>();
                colors.add(Color.RED);
                colors.add(Color.BLUE);

                PieDataSet pieDataSet=new PieDataSet(pieEntries,"");
                pieDataSet.setValueTextSize(30);
                pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                pieDataSet.setValueLinePart1Length(1.2f);


                pieDataSet.setColors(colors);
                pieDataSet.setValueFormatter(new PercentFormatter());


                PieData pieData=new PieData(pieDataSet);
                pc15_1.setData(pieData);
                pc15_1.setHoleRadius(0);
                pc15_1.setTransparentCircleRadius(0);
                pc15_1.invalidate();
            }
        });
    }

    private void setYAxis() {
    }

    private void setXAxis() {
    }

    private void setLegend() {
        Legend legend=pc15_1.getLegend();
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTextSize(20);
    }

    private void initView(View view) {
        pc15_1 = (PieChart) view.findViewById(R.id.pc15_1);
    }
}
