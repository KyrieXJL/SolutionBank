package com.lenovo.smarttraffic;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
public class Fragment34_1 extends Fragment implements View.OnClickListener {


    private EditText etext34_1_1;
    private Button btn34_1_1;
    private Button btn34_1_2;
    private Button btn34_1_3;
    private EditText etext34_1_2;
    private Button btn34_1_4;
    private SQLHelper sqlHelper;

    public Fragment34_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment34_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        etext34_1_1 = (EditText) view.findViewById(R.id.etext34_1_1);
        btn34_1_1 = (Button) view.findViewById(R.id.btn34_1_1);
        btn34_1_2 = (Button) view.findViewById(R.id.btn34_1_2);
        btn34_1_3 = (Button) view.findViewById(R.id.btn34_1_3);
        etext34_1_2 = (EditText) view.findViewById(R.id.etext34_1_2);
        btn34_1_4 = (Button) view.findViewById(R.id.btn34_1_4);

        btn34_1_1.setOnClickListener(this);
        btn34_1_2.setOnClickListener(this);
        btn34_1_3.setOnClickListener(this);
        btn34_1_4.setOnClickListener(this);
        sqlHelper=new SQLHelper(getContext(),"Car34_1.db",null,1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn34_1_1:
                etext34_1_2.setText("10");

                break;
            case R.id.btn34_1_2:
                etext34_1_2.setText("50");

                break;
            case R.id.btn34_1_3:
                etext34_1_2.setText("100");

                break;
            case R.id.btn34_1_4:
                if (TextUtils.isEmpty(etext34_1_1.getText().toString())){
                    Toast.makeText(getContext(), "请输入所需充值的车牌号", Toast.LENGTH_SHORT).show();

                }else {
                    if (TextUtils.isEmpty(etext34_1_2.getText().toString())){
                        Toast.makeText(getContext(), "请输入充值金额", Toast.LENGTH_SHORT).show();

                    }else if (Integer.valueOf(etext34_1_2.getText().toString())<0||Integer.valueOf(etext34_1_2.getText().toString())>999){
                        Toast.makeText(getContext(), "请输入正确金额", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "充值成功", Toast.LENGTH_SHORT).show();
                        SQLiteDatabase db=sqlHelper.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        values.put("card","鲁"+etext34_1_1.getText().toString());
                        values.put("money",Integer.valueOf(etext34_1_2.getText().toString()));
                        Date date=new Date();
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String dt=simpleDateFormat.format(date);
                        values.put("date",dt);
                        db.insert("car34",null,values);
                        sqlHelper.close();
                    }
                }


                break;
        }
    }

}
