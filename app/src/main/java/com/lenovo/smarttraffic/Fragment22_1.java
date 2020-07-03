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

import com.lenovo.smarttraffic.Adapter.Adapter22_1;
import com.lenovo.smarttraffic.Entity.Car22_1;
import com.lenovo.smarttraffic.Helper.SQLHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment22_1 extends Fragment {


    private TextView text22_1_1;
    private ListView listview22_1;
    private List<Car22_1> car22_1s;
    private Adapter22_1 adapter22_1;
    private SQLHelper sqlHelper;

    private Car22_1 car22_1;
    public Fragment22_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment22_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text22_1_1 = (TextView) view.findViewById(R.id.text22_1_1);
        listview22_1 = (ListView) view.findViewById(R.id.listview22_1);
        sqlHelper=new SQLHelper(getContext(),"Car221.db",null,1);
        car22_1s=new ArrayList<>();
        adapter22_1=new Adapter22_1(getContext(),R.layout.fragment22_1_list,car22_1s);
        listview22_1.setAdapter(adapter22_1);
        SQLiteDatabase db=sqlHelper.getWritableDatabase();
        sqlHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT*FROM car22",null);
        if (cursor.moveToFirst()){
            do {
                String date=cursor.getString(cursor.getColumnIndex("date"));
                String user=cursor.getString(cursor.getColumnIndex("user"));
                String carid=cursor.getString(cursor.getColumnIndex("carid"));
                int money=cursor.getInt(cursor.getColumnIndex("money"));
                int balance=cursor.getInt(cursor.getColumnIndex("balance"));
                car22_1=new Car22_1(date,user,carid,money,balance);
                car22_1s.add(car22_1);
            }while (cursor.moveToNext());
        }
        adapter22_1.notifyDataSetChanged();
        int count=0;
        for (Car22_1 car22_1:car22_1s){
            count=count+car22_1.getMoney();
        }
        text22_1_1.setText("总支出："+count);
        sqlHelper.close();
    }
}
