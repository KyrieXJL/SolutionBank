package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter02;
import com.lenovo.smarttraffic.Entity.Car02;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment02 extends Fragment implements View.OnClickListener {


    private Spinner spinner02;
    private ListView listview02;
    private List<Car02> car02s;
    private Adapter02 adapter02;
    private HttpHelper httpHelper;
    private Gson gson;
    private Button btn02;

    public Fragment02() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment02, container, false);
        initView(view);
 //       View head = LayoutInflater.from(getContext()).inflate(R.layout.fragment02_head, null, false);
//         listview02.addHeaderView(head);
        send1();
        return view;
    }

    private void initView(View view) {
        btn02 = (Button) view.findViewById(R.id.btn02);
        btn02.setOnClickListener(this);
        spinner02 = (Spinner) view.findViewById(R.id.spinner02);
        listview02 = (ListView) view.findViewById(R.id.listview02);
        String[] data = {"路口升序", "路口降序", "红灯升序", "红灯降序"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, data);
        spinner02.setAdapter(adapter);
        car02s = new ArrayList<>();
        adapter02 = new Adapter02(getContext(), R.layout.fragment02_list, car02s);
        listview02.setAdapter(adapter02);
        httpHelper = HttpHelper.getInstance(getContext());
        gson = new Gson();



    }
    private void send2() {
        car02s.clear();
        httpHelper.PostJosn("P2_1", "{\"type\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car02> car02=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car02>>(){}.getType());
                Collections.sort(car02, new Comparator<Car02>() {
                    @Override
                    public int compare(Car02 car02, Car02 t1) {
                        if (car02.getWay()>t1.getWay()){
                            return -1;
                        }else if (car02.getWay()==t1.getWay()){
                            return 0;
                        }else {
                            return 1;
                        }
                    }
                });
                car02s.addAll(car02);
                adapter02.notifyDataSetChanged();
            }
        });

    }

    private void send1() {
        car02s.clear();
        httpHelper.PostJosn("P2_1", "{\"type\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car02> car02=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car02>>(){}.getType());
                Collections.sort(car02, new Comparator<Car02>() {
                    @Override
                    public int compare(Car02 car02, Car02 t1) {
                        if (car02.getWay()>t1.getWay()){
                            return 1;
                        }else if (car02.getWay()==t1.getWay()){
                            return 0;
                        }else {
                            return -1;
                        }
                    }
                });
                car02s.addAll(car02);
                adapter02.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn02:
                String a= String.valueOf(spinner02.getSelectedItem());
                if (a.equals("路口升序")){
                    send1();
                }else if (a.equals("路口降序")){
                    send2();
                }

                break;
        }
    }
}
