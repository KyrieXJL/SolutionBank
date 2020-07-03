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
import com.lenovo.smarttraffic.Entity.Car15_2;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment15_2 extends Fragment {


    private PieChart pc15_2;
    private HttpHelper httpHelper;
    private Gson gson;

    public Fragment15_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment15_2, container, false);
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
                List<Car15_2> car15_2s=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car15_2>>(){}.getType());
                String[] store=new String[car15_2s.size()];
                int a=0;
                for (Car15_2 car15_2:car15_2s){
                    store[a]=car15_2.getCarno();
                    a++;
                }

                int count=0;
                int[] log=new int[car15_2s.size()];
                for (int i = 0; i < car15_2s.size(); i++) {
                    for (int j = i+1; j <car15_2s.size() ; j++) {
                        if (store[i].equals(store[j])){
                            store[j]="null"+j;
                            log[i]++;
                            count++;
                        }
                    }
                }
                int count1=0;
                for (int i = 0; i < car15_2s.size(); i++) {
                    if (log[i]>=1){
                        count1++;
                    }
                }

                int count2=count+count1;//算出违章重复的个数

                List<PieEntry> pieEntries=new ArrayList<>();
                pieEntries.add(new PieEntry(count2,"有重复违章"));
                pieEntries.add(new PieEntry(car15_2s.size()-count2,"无重复违章"));


                List<Integer> colocrs=new ArrayList<>();
                colocrs.add(Color.RED);
                colocrs.add(Color.BLUE);

                PieDataSet pieDataSet=new PieDataSet(pieEntries,"");
                pieDataSet.setValueTextSize(30);//图片上文字字体大小
                pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//设置数据显示在圆外
                pieDataSet.setValueLinePart1Length(1.2f);  //当值位置为外边线时，表示线的前半段长度
                pieDataSet.setColors(colocrs);
                pieDataSet.setValueFormatter(new PercentFormatter());

                PieData pieData=new PieData(pieDataSet);
                pc15_2.setData(pieData);
                pc15_2.setHoleRadius(0);  //设置是否为空心圆
                pc15_2.setTransparentCircleRadius(0);  //设置中间是否为透明
                pc15_2.invalidate();




            }
        });

    }

    private void setYAxis() {
    }

    private void setXAxis() {

    }

    private void setLegend() {
        Legend legend=pc15_2.getLegend();
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTextSize(20);
    }

    private void initView(View view) {
        pc15_2 = (PieChart) view.findViewById(R.id.pc15_2);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
    }
}
