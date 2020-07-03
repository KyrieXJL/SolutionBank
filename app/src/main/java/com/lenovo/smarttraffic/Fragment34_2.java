package com.lenovo.smarttraffic;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.Helper.SQLHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment34_2 extends Fragment implements View.OnClickListener {


    private EditText etext34_2_1;
    private TextView text34_2_1;
    private Button btn34_2_1;
    private SQLHelper sqlHelper;

    public Fragment34_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment34_2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        etext34_2_1 = (EditText) view.findViewById(R.id.etext34_2_1);
        text34_2_1 = (TextView) view.findViewById(R.id.text34_2_1);
        btn34_2_1 = (Button) view.findViewById(R.id.btn34_2_1);

        btn34_2_1.setOnClickListener(this);
        sqlHelper=new SQLHelper(getContext(),"Car34_1.db",null,1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn34_2_1:
                if (TextUtils.isEmpty(etext34_2_1.getText().toString())){
                    Toast.makeText(getContext(), "请输入查询的车辆编号", Toast.LENGTH_SHORT).show();
                }else {
                    SQLiteDatabase db=sqlHelper.getWritableDatabase();
                    sqlHelper.getReadableDatabase();
                    Cursor cursor=db.rawQuery("SELECT*FROM car34",null );
                    /*int count=0,count1=0;
                    if (cursor.moveToFirst()){
                        do {
                            count1++;
                            int id=cursor.getInt(cursor.getColumnIndex("id"));
                            if (Integer.valueOf(etext34_2_1.getText().toString())>id){
                                count++;
                               // Toast.makeText(getContext(), "未查询到", Toast.LENGTH_SHORT).show();
                            }

                            *//*else {

                                Cursor cursor1=db.rawQuery("SELECT*FROM car34 where id="+Integer.valueOf(etext34_2_1.getText().toString()),null);
                               // Cursor cursor1=db.rawQuery("SELECT*FROM car34 where id="+Integer.valueOf(text34_2.getText().toString()),null);
                                if (cursor1.moveToFirst()){
                                    do {
                                        int money=cursor.getInt(cursor.getColumnIndex("money"));
                                        text34_2_1.setText(money+"");
                                    }while (cursor1.moveToNext());
                                }
                            }*//*

                        }while (cursor.moveToNext());
                    }*/

                /*    if (count==count1){
                        Toast.makeText(getContext(), "未查询到", Toast.LENGTH_SHORT).show();
                        text34_2_1.setText("");
                    }else {*/
                    Cursor cursor1=db.rawQuery("SELECT*FROM car34 where id="+Integer.valueOf(etext34_2_1.getText().toString()),null);
                    if(cursor1.getCount()!=0){
                        if (cursor1.moveToFirst()){
                            do {
                                int money=cursor1.getInt(cursor.getColumnIndex("money"));
                                text34_2_1.setText(money+"");
                            }while (cursor1.moveToNext());
                        }
                    }else {
                        Toast.makeText(getContext(), "未查询到", Toast.LENGTH_SHORT).show();
                        text34_2_1.setText("");

                    }
                        // Cursor cursor1=db.rawQuery("SELECT*FROM car34 where id="+Integer.valueOf(text34_2.getText().toString()),null);
                  /*      if (cursor1.moveToFirst()){
                            do {
                                int money=cursor1.getInt(cursor.getColumnIndex("money"));
                                text34_2_1.setText(money+"");
                            }while (cursor1.moveToNext());
                        }*/
                  db.close();
                    }

                sqlHelper.close();
                break;
        }
    }


}
