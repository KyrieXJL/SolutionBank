package com.lenovo.smarttraffic;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lenovo.smarttraffic.Adapter.Adapter31;
import com.lenovo.smarttraffic.Entity.Car31;
import com.lenovo.smarttraffic.Helper.SQLHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment31_1 extends Fragment {


    private ListView listview31;
    private SQLHelper sqlHelper;
    private List<Car31> car31s;
    private Adapter31 adapter31;

    private Car31 car31;
    public Fragment31_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment31_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listview31 = (ListView) view.findViewById(R.id.listview31);
        sqlHelper=new SQLHelper(getContext(),"Car31_1.db",null,1);
        car31s=new ArrayList<>();
        adapter31=new Adapter31(getContext(),R.layout.fragment31_list,car31s);
        listview31.setAdapter(adapter31);
        SQLiteDatabase db=sqlHelper.getWritableDatabase();
        sqlHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT*FROM car31",null);
        if (cursor.moveToFirst()){
            do {
                String biaoti=cursor.getString(cursor.getColumnIndex("biaoti"));
                String date=cursor.getString(cursor.getColumnIndex("date"));
                String zhuangtai=cursor.getString(cursor.getColumnIndex("zhuangtai"));
                String neirong=cursor.getString(cursor.getColumnIndex("neirong"));
                String tel=cursor.getString(cursor.getColumnIndex("tel"));
                car31=new Car31(biaoti,date,zhuangtai,neirong,tel);
                car31s.add(car31);
            }while (cursor.moveToNext());
        }
        adapter31.notifyDataSetChanged();
    }
}
