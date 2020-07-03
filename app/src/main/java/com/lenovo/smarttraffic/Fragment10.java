package com.lenovo.smarttraffic;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter10;
import com.lenovo.smarttraffic.Adapter.Adapter10_item;
import com.lenovo.smarttraffic.Entity.Car10;
import com.lenovo.smarttraffic.Entity.Car10_item;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment10 extends Fragment implements View.OnClickListener {


    private TextView text10;
    private Button btn10;
    private ExpandableListView elistview10;
    private List<Car10> car10s;
    private Adapter10 adapter10;
    private HttpHelper httpHelper;
    private Gson gson;


    private List<Car10_item> car10_items;
    private Adapter10_item adapter10_item;
    private Timer timer;


    public Fragment10() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment10, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text10 = (TextView) view.findViewById(R.id.text10);
        btn10 = (Button) view.findViewById(R.id.btn10);
        elistview10 = (ExpandableListView) view.findViewById(R.id.elistview10);

        btn10.setOnClickListener(this);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
        car10s=new ArrayList<>();
        adapter10=new Adapter10(car10s);
        elistview10.setAdapter(adapter10);
        send();
    }

    private void send() {
        httpHelper.PostJosn("P10_1", "{\"cid\":0,\"sid\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car10> car10=gson.fromJson(jsonObject.optJSONObject("serverinfo").optJSONArray("data").toString(),new TypeToken<List<Car10>>(){}.getType());
                for (Car10 car101:car10){
                    List<Car10.CarsBean> carsBeans=car101.getCars();
                    Collections.sort(carsBeans, (carsBean, t1) -> {
                        if (carsBean.getDistance()>t1.getDistance()){
                            return 1;
                        }else if (carsBean.getDistance()==t1.getDistance()){
                            return 0;
                        }else {
                            return -1;
                        }
                    });
                    car10s.clear();
                    car10s.addAll(car10);
                    adapter10.notifyDataSetChanged();
                 }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn10:
                send1();

                break;
        }
    }

    private void send1() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View in=LayoutInflater.from(getContext()).inflate(R.layout.fragment10_item,null,false);
        TextView heji=in.findViewById(R.id.text10_item);
        ListView listView=in.findViewById(R.id.listview10);
        car10_items=new ArrayList<>();
        adapter10_item=new Adapter10_item(getContext(),R.layout.fragment10_item_list,car10_items);
        listView.setAdapter(adapter10_item);

        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                httpHelper.PostJosn("P10_2", "{}", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        car10_items.clear();
                        List<Car10_item> car10_item=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car10_item>>(){}.getType());
                        int count=0;
                        for (Car10_item car10_item1:car10_item){
                            count=count+car10_item1.getPersons();
                        }
                        heji.setText(count+"");
                        text10.setText("当前承载能力："+count+"人");
                        car10_items.addAll(car10_item);
                        adapter10_item.notifyDataSetChanged();
                    }
                });
            }
        },0,3000);

        builder.setPositiveButton("取消",null);
        builder.setView(in);
        builder.create().show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
        }
    }
}
