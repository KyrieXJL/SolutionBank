package com.lenovo.smarttraffic;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.lenovo.smarttraffic.Helper.HttpHelper;
import com.lenovo.smarttraffic.Helper.SQLHelper;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment05 extends Fragment {


    private TextView text05_1;
    private LinearLayout linear05_1;
    private TextView text05_2;
    private LinearLayout linear05_2;
    private TextView text05_3;
    private LinearLayout linear05_3;
    private TextView text05_4;
    private LinearLayout linear05_4;
    private TextView text05_5;
    private LinearLayout linear05_5;
    private TextView text05_6;
    private LinearLayout linear05_6;
    private Timer timer;
    private HttpHelper httpHelper;

    private int a1,a2,a3,a4,a5,a6;
    private SQLHelper sqlHelper;
    private FragmentManager fragmentManager;

    public Fragment05() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment05, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text05_1 = (TextView) view.findViewById(R.id.text05_1);
        linear05_1 = (LinearLayout) view.findViewById(R.id.linear05_1);
        text05_2 = (TextView) view.findViewById(R.id.text05_2);
        linear05_2 = (LinearLayout) view.findViewById(R.id.linear05_2);
        text05_3 = (TextView) view.findViewById(R.id.text05_3);
        linear05_3 = (LinearLayout) view.findViewById(R.id.linear05_3);
        text05_4 = (TextView) view.findViewById(R.id.text05_4);
        linear05_4 = (LinearLayout) view.findViewById(R.id.linear05_4);
        text05_5 = (TextView) view.findViewById(R.id.text05_5);
        linear05_5 = (LinearLayout) view.findViewById(R.id.linear05_5);
        text05_6 = (TextView) view.findViewById(R.id.text05_6);
        linear05_6 = (LinearLayout) view.findViewById(R.id.linear05_6);
        httpHelper=HttpHelper.getInstance(getContext());
        sqlHelper=new SQLHelper(getContext(),"Car05.db",null,1);
        fragmentManager=getFragmentManager();
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                send1();
                send2();
                send3();
                send4();
                send5();
                send6();
                SQLiteDatabase db=sqlHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //tem integer,hum integer,light integer,co2 integer,pm25 integer,status integer
                values.put("tem",a1);
                values.put("hum",a2);
                values.put("light",a3);
                values.put("co2",a4);
                values.put("pm25",a5);
                values.put("status",a6);
                db.insert("car05",null,values);
                sqlHelper.close();




            }
        },0,3000);



    }
    private void send6() {
        httpHelper.PostJosn("P5_6", "{\"way\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                a6=jsonObject.optJSONObject("serverinfo").optInt("status");
                text05_6.setText(a6+"");
                if (a6>4){
                    linear05_6.setBackgroundColor(Color.RED);
                }else {
                    linear05_6.setBackgroundColor(Color.GREEN);
                }
                linear05_6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment05_1 fragment05_1=new Fragment05_1();
                        Bundle bundle=new Bundle();
                        bundle.putInt("value",a6);
                        fragment05_1.setArguments(bundle);

                        fragmentManager.beginTransaction().replace(R.id.container,fragment05_1).commit();
                    }
                });
            }
        });
    }
    private void send5() {
        httpHelper.PostJosn("P5_5", "{\"way\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                a5=jsonObject.optJSONObject("serverinfo").optInt("pm25");
                text05_5.setText(a5+"");
                if (a5>254){
                    linear05_5.setBackgroundColor(Color.RED);
                }else {
                    linear05_5.setBackgroundColor(Color.GREEN);
                }
                linear05_5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment05_1 fragment05_1=new Fragment05_1();
                        Bundle bundle=new Bundle();
                        bundle.putInt("value",a5);
                        fragment05_1.setArguments(bundle);

                        fragmentManager.beginTransaction().replace(R.id.container,fragment05_1).commit();
                    }
                });
            }
        });
    }
    private void send4() {
        httpHelper.PostJosn("P5_3", "{\"way\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                a4=jsonObject.optJSONObject("serverinfo").optInt("co2");
                text05_4.setText(a4+"");
                if (a4>324){
                    linear05_4.setBackgroundColor(Color.RED);
                }else {
                    linear05_4.setBackgroundColor(Color.GREEN);
                }
                linear05_4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment05_1 fragment05_1=new Fragment05_1();
                        Bundle bundle=new Bundle();
                        bundle.putInt("value",a4);
                        fragment05_1.setArguments(bundle);

                        fragmentManager.beginTransaction().replace(R.id.container,fragment05_1).commit();
                    }
                });
            }
        });
    }
    private void send3() {
        httpHelper.PostJosn("P5_4", "{\"way\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                a3=jsonObject.optJSONObject("serverinfo").optInt("light");
                text05_3.setText(a3+"");
                if (a3>567){
                    linear05_3.setBackgroundColor(Color.RED);
                }else {
                    linear05_3.setBackgroundColor(Color.GREEN);
                }
                linear05_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment05_1 fragment05_1=new Fragment05_1();
                        Bundle bundle=new Bundle();
                        bundle.putInt("value",a3);
                        fragment05_1.setArguments(bundle);

                        fragmentManager.beginTransaction().replace(R.id.container,fragment05_1).commit();
                    }
                });
            }
        });
    }
    private void send2() {
        httpHelper.PostJosn("P5_2", "{\"way\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                a2=jsonObject.optJSONObject("serverinfo").optInt("hum");
                text05_2.setText(a2+"");
                if (a2>80){
                    linear05_2.setBackgroundColor(Color.RED);
                }else {
                    linear05_2.setBackgroundColor(Color.GREEN);
                }
                linear05_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment05_1 fragment05_1=new Fragment05_1();
                        Bundle bundle=new Bundle();
                        bundle.putInt("value",a2);
                        fragment05_1.setArguments(bundle);

                        fragmentManager.beginTransaction().replace(R.id.container,fragment05_1).commit();
                    }
                });
            }
        });
    }

    private void send1() {
        httpHelper.PostJosn("P5_1", "{\"way\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                a1=jsonObject.optJSONObject("serverinfo").optInt("tem");
                text05_1.setText(a1+"");
                if (a1>32){
                    linear05_1.setBackgroundColor(Color.RED);
                }else {
                    linear05_1.setBackgroundColor(Color.GREEN);
                }
                linear05_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment05_1 fragment05_1=new Fragment05_1();
                        Bundle bundle=new Bundle();
                        bundle.putInt("value",a1);
                        fragment05_1.setArguments(bundle);
                        fragmentManager.beginTransaction().replace(R.id.container,fragment05_1).commit();
                    }
                });
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
        }
    }
}
