package com.lenovo.smarttraffic;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lenovo.smarttraffic.Helper.SQLHelper;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment31 extends Fragment implements View.OnClickListener {


    private Button btn31;
    private EditText etext31_1;
    private EditText etext31_2;
    private EditText etext31_3;
    private Button btn31_1;
    private SQLHelper sqlHelper;

    public Fragment31() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment31, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btn31 = (Button) view.findViewById(R.id.btn31);
        etext31_1 = (EditText) view.findViewById(R.id.etext31_1);
        etext31_2 = (EditText) view.findViewById(R.id.etext31_2);
        etext31_3 = (EditText) view.findViewById(R.id.etext31_3);
        btn31_1 = (Button) view.findViewById(R.id.btn31_1);

        btn31.setOnClickListener(this);
        btn31_1.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn31:
                Fragment31_1 fragment31_1=new Fragment31_1();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,fragment31_1).commit();

                break;
            case R.id.btn31_1:
                Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
                sqlHelper=new SQLHelper(getContext(),"Car31_1.db",null,1);
                SQLiteDatabase db=sqlHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //id integer primary key autoincrement,biaoti text,date text,zhuangtai text,neirong text,tel text)
                values.put("biaoti",etext31_1.getText().toString());
                Date date=new Date();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
                String dt=simpleDateFormat.format(date);
                values.put("date",dt);
                values.put("zhuangtai","未受理");
                values.put("neirong",etext31_2.getText().toString());
                values.put("tel",etext31_3.getText().toString());
                db.insert("car31",null,values);
                sqlHelper.close();


                break;
        }
    }

}
