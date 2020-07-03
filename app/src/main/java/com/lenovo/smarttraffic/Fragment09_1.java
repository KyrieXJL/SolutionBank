package com.lenovo.smarttraffic;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lenovo.smarttraffic.Adapter.Adapter09_1;
import com.lenovo.smarttraffic.Entity.Car09_1;
import com.lenovo.smarttraffic.Helper.SQLHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment09_1 extends Fragment {


    private TextView text09;
    private ListView listview09_1;
    private List<Car09_1> car09_1s;
    private Adapter09_1 adapter09_1;
    private SQLHelper sqlHelper;
    private Car09_1 car09_1;

    public Fragment09_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment09_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text09 = (TextView) view.findViewById(R.id.text09);
        listview09_1 = (ListView) view.findViewById(R.id.listview09_1);
        car09_1s=new ArrayList<>();
        adapter09_1=new Adapter09_1(getContext(),R.layout.fragment09_1_list,car09_1s);
        listview09_1.setAdapter(adapter09_1);
        sqlHelper=new SQLHelper(getContext(),"Car0921.db",null,1);
        SQLiteDatabase db=sqlHelper.getWritableDatabase();
        sqlHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT*FROM car092",null);
        if (cursor.moveToFirst()){
            do {
                String date=cursor.getString(cursor.getColumnIndex("date"));
                String user=cursor.getString(cursor.getColumnIndex("user"));
                String card=cursor.getString(cursor.getColumnIndex("card"));
                int money=cursor.getInt(cursor.getColumnIndex("money"));
                int balance=cursor.getInt(cursor.getColumnIndex("balance"));
                car09_1=new Car09_1(date,user,card,money,balance,date);
                car09_1s.add(car09_1);
            }while (cursor.moveToNext());
        }
        adapter09_1.notifyDataSetChanged();
        int count=0;
        for (Car09_1 car09_1:car09_1s){
            count=count+car09_1.getMoney();
        }
        text09.setText("充值总额为："+count);
        sqlHelper.close();
    }
}
