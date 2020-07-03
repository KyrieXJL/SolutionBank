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

import com.lenovo.smarttraffic.Adapter.Adapter34;
import com.lenovo.smarttraffic.Entity.Car34;
import com.lenovo.smarttraffic.Helper.SQLHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment34_3 extends Fragment {


    private TextView text34_3_1;
    private ListView listview34;
    private List<Car34> car34s;
    private Adapter34 adapter34;
    private SQLHelper sqlHelper;
    private Car34 car34;

    public Fragment34_3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment34_3, container, false);
        initView(view);
        View head=LayoutInflater.from(getContext()).inflate(R.layout.fragment34_head,null,false);
        listview34.addHeaderView(head);
        return view;
    }

    private void initView(View view) {
        text34_3_1 = (TextView) view.findViewById(R.id.text34_3_1);
        listview34 = (ListView) view.findViewById(R.id.listview34);
        car34s=new ArrayList<>();
        adapter34=new Adapter34(getContext(),R.layout.fragment34_3_list,car34s);
        listview34.setAdapter(adapter34);
        sqlHelper=new SQLHelper(getContext(),"Car34_1.db",null,1);
        SQLiteDatabase db=sqlHelper.getWritableDatabase();
        sqlHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT*FROM car34",null );
        if (cursor.moveToFirst()){
            do {
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String card=cursor.getString(cursor.getColumnIndex("card"));
                int money=cursor.getInt(cursor.getColumnIndex("money"));
                String date=cursor.getString(cursor.getColumnIndex("date"));
                car34=new Car34(id,card,money,date);
                car34s.add(car34);
            }while (cursor.moveToNext());
        }
        int count=0;
        for (Car34 car34:car34s){
            count=count+car34.getMoney();
        }
        text34_3_1.setText("充值合计："+count+"元");
        adapter34.notifyDataSetChanged();
        sqlHelper.close();
    }
}
