package com.lenovo.smarttraffic;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.lenovo.smarttraffic.Helper.SQLHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment06_3 extends Fragment {


    private LineChart lchart06_3;
    private SQLHelper sqlHelper;

    public Fragment06_3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment06_3, container, false);
        initView(view);
        setLegend();
        setXAxis();
        setYAixs();
        setData();
        return view;
    }

    private void setData() {
        SQLiteDatabase db=sqlHelper.getWritableDatabase();
        sqlHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT*FROM car05",null);
        List<Entry> entries=new ArrayList<>();
        int i=0;
        if (cursor.moveToFirst()){
            do {
                int tem=cursor.getInt(cursor.getColumnIndex("tem"));
                if (i<20){
                    entries.add(new Entry(i,tem));
                    i++;
                }
            }while (cursor.moveToNext());
        }
        sqlHelper.close();
        LineDataSet lineDataSet=new LineDataSet(entries,"");
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setCircleColor(Color.BLACK);
        lineDataSet.setColor(Color.BLACK);
        lineDataSet.setCircleRadius(10);

        LineData lineData=new LineData(lineDataSet);
        lineData.setDrawValues(false);
        lchart06_3.setData(lineData);
        lchart06_3.animateX(20000);

    }

    private void setYAixs() {
        YAxis yAxis=lchart06_3.getAxisLeft();
        yAxis.setAxisMaximum(38);
        yAxis.setAxisMinimum(0);
        yAxis.setTextSize(15);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value)+"℃";
            }
        });
        lchart06_3.getAxisRight().setEnabled(false);
    }

    private void setXAxis() {
        XAxis xAxis=lchart06_3.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(30);
        xAxis.setLabelRotationAngle(90);
        xAxis.setDrawGridLines(false);
        String[] data=new String[]{"55:03","55:06","55:09","55:12","55:15","55:18",
                "55:21","55:24","55:27","55:30","55:33","55:36","55:39"
                ,"55:42","55:45","55:48","55:51","55:54","55:57","56:00"};
        xAxis.setLabelCount(data.length);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return data[(int) value];
            }
        });

    }

    private void setLegend() {
        Legend legend=lchart06_3.getLegend();
        legend.setEnabled(false);
    }

    private void initView(View view) {
        lchart06_3 = (LineChart) view.findViewById(R.id.lchart06_3);
        sqlHelper=new SQLHelper(getContext(),"Car05.db",null,1);
    }
}
