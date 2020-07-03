package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter21;
import com.lenovo.smarttraffic.Entity.Car21;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment21 extends Fragment {


    private Spinner spinner21;
    private ListView listview21;
    private List<Car21> car21s;
    private Adapter21 adapter21;
    private HttpHelper httpHelper;
    private Gson gson;

    public Fragment21() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment21, container, false);
        initView(view);



        return view;
    }

    private void initView(View view) {
        spinner21 = (Spinner) view.findViewById(R.id.spinner21);
        listview21 = (ListView) view.findViewById(R.id.listview21);
        View head=LayoutInflater.from(getContext()).inflate(R.layout.fragment21_head,null,false);
        String[] data={"路口升序","路口降序","红灯升序","红灯降序","黄灯升序"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,data);
        spinner21.setAdapter(adapter);
        listview21.addHeaderView(head);
        car21s=new ArrayList<>();
        adapter21=new Adapter21(getContext(),R.layout.fragment21_list,car21s);
        listview21.setAdapter(adapter21);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
        //send1();
        spinner21.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String a=spinner21.getSelectedItem().toString();
                if (a=="路口升序"){
                    send1();
                }else if (a=="路口降序"){
                    send2();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }private void send1() {
        car21s.clear();
        httpHelper.PostJosn("P2_1", "{\"type\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car21> car21=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car21>>(){}.getType());
                Collections.sort(car21, new Comparator<Car21>() {
                    @Override
                    public int compare(Car21 car21, Car21 t1) {
                        if (car21.getWay()>t1.getWay()){
                            return 1;
                        }else if (car21.getWay()==t1.getWay()){
                            return 0;
                        }else {
                            return -1;
                        }
                    }
                });
                car21s.addAll(car21);
                adapter21.notifyDataSetChanged();
            }
        });
    }
    private void send2() {
        car21s.clear();
        httpHelper.PostJosn("P2_1", "{\"type\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car21> car21=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car21>>(){}.getType());
                Collections.sort(car21, new Comparator<Car21>() {
                    @Override
                    public int compare(Car21 car21, Car21 t1) {
                        if (car21.getWay()>t1.getWay()){
                            return -1;
                        }else if (car21.getWay()==t1.getWay()){
                            return 0;
                        }else {
                            return 1;
                        }
                    }
                });
                car21s.addAll(car21);
                adapter21.notifyDataSetChanged();
            }
        });
    }
}
