package com.lenovo.smarttraffic;


import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.Helper.SQLHelper;
import com.lenovo.smarttraffic.bean.Notification;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment07 extends Fragment implements View.OnClickListener {


    private TextView text07;
    private Switch switch07;
    private EditText etext07_1;
    private EditText etext07_2;
    private EditText etext07_3;
    private EditText etext07_4;
    private EditText etext07_5;
    private EditText etext07_6;
    private Button btn07;
    private SQLHelper sqlHelper;
    private Timer timer;

    public Fragment07() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment07, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text07 = (TextView) view.findViewById(R.id.text07);
        switch07 = (Switch) view.findViewById(R.id.switch07);
        etext07_1 = (EditText) view.findViewById(R.id.etext07_1);
        etext07_2 = (EditText) view.findViewById(R.id.etext07_2);
        etext07_3 = (EditText) view.findViewById(R.id.etext07_3);
        etext07_4 = (EditText) view.findViewById(R.id.etext07_4);
        etext07_5 = (EditText) view.findViewById(R.id.etext07_5);
        etext07_6 = (EditText) view.findViewById(R.id.etext07_6);
        btn07 = (Button) view.findViewById(R.id.btn07);

        btn07.setOnClickListener(this);

        sqlHelper=new SQLHelper(getContext(),"Car05.db",null,1);
        switch07.setEnabled(true);
        switch07.setChecked(true);
        text07.setText("开");
        etext07_1.setEnabled(false);
        etext07_2.setEnabled(false);
        etext07_3.setEnabled(false);
        etext07_4.setEnabled(false);
        etext07_5.setEnabled(false);
        etext07_6.setEnabled(false);
        btn07.setEnabled(false);

        SQLiteDatabase db=sqlHelper.getWritableDatabase();
        sqlHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT*FROM car05",null);
        if (cursor.moveToFirst()){
            do {
                //tem integer,hum integer,light integer,co2 integer,pm25 integer,status integer
                int a1=cursor.getInt(cursor.getColumnIndex("tem"));
                int a2=cursor.getInt(cursor.getColumnIndex("hum"));
                int a3=cursor.getInt(cursor.getColumnIndex("light"));
                int a4=cursor.getInt(cursor.getColumnIndex("co2"));
                int a5=cursor.getInt(cursor.getColumnIndex("pm25"));
                int a6=cursor.getInt(cursor.getColumnIndex("status"));
                etext07_1.setText(a1+"");
                etext07_2.setText(a2+"");
                etext07_3.setText(a3+"");
                etext07_4.setText(a4+"");
                etext07_5.setText(a5+"");
                etext07_6.setText(a6+"");
            }while (cursor.moveToNext());
        }
        sqlHelper.close();
        //刚启动为开启状态
        info();

        switch07.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){

                    text07.setText("开");
                    etext07_1.setEnabled(false);
                    etext07_2.setEnabled(false);
                    etext07_3.setEnabled(false);
                    etext07_4.setEnabled(false);
                    etext07_5.setEnabled(false);
                    etext07_6.setEnabled(false);
                    btn07.setEnabled(false);
                    info();
                }else {
                    timer.cancel();
                    etext07_1.setEnabled(true);
                    etext07_2.setEnabled(true);
                    etext07_3.setEnabled(true);
                    etext07_4.setEnabled(true);
                    etext07_5.setEnabled(true);
                    etext07_6.setEnabled(true);
                    btn07.setEnabled(true);
                    text07.setText("关");
                }
            }
        });

    }

    private void info() {
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (Integer.valueOf(etext07_1.getText().toString())>32){
                    NotificationManager notificationManager= (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationCompat.Builder builder=new NotificationCompat.Builder(getContext());
                    builder.setSmallIcon(R.drawable.car04).setContentTitle("温度报警").setContentText("阈值为32，当前阈值为："+etext07_1.getText().toString());
                    notificationManager.notify(1,builder.build());
               }
                 if (Integer.valueOf(etext07_2.getText().toString())>80){
                    NotificationManager notificationManager= (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationCompat.Builder builder=new NotificationCompat.Builder(getContext());
                    builder.setSmallIcon(R.drawable.car04).setContentTitle("湿度报警").setContentText("阈值为80，当前阈值为："+etext07_2.getText().toString());
                    notificationManager.notify(1,builder.build());
                }
                if (Integer.valueOf(etext07_3.getText().toString())>567){
                    NotificationManager notificationManager= (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationCompat.Builder builder=new NotificationCompat.Builder(getContext());
                    builder.setSmallIcon(R.drawable.car04).setContentTitle("光照强度报警").setContentText("阈值为567，当前阈值为："+etext07_3.getText().toString());
                    notificationManager.notify(1,builder.build());
                }
            }
        },0,3000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn07:
                sqlHelper=new SQLHelper(getContext(),"Car05.db",null,1);
                SQLiteDatabase db=sqlHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("tem",Integer.valueOf(etext07_1.getText().toString()));
                values.put("hum",Integer.valueOf(etext07_2.getText().toString()));
                values.put("light",Integer.valueOf(etext07_3.getText().toString()));
                values.put("co2",Integer.valueOf(etext07_4.getText().toString()));
                values.put("pm25",Integer.valueOf(etext07_5.getText().toString()));
                values.put("status",Integer.valueOf(etext07_6.getText().toString()));
                db.insert("car05",null,values);
                sqlHelper.close();
                Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();


                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
        }
    }
}
