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

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter03;
import com.lenovo.smarttraffic.Entity.Car03;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment03 extends Fragment implements View.OnClickListener {


    private Spinner spinner03;
    private Button btn03;
    private ListView listview03;
    private List<Car03> car03s;
    private Adapter03 adapter03;
    private HttpHelper httpHelper;
    private Gson gson;

    public Fragment03() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment03, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        spinner03 = (Spinner) view.findViewById(R.id.spinner03);
        btn03 = (Button) view.findViewById(R.id.btn03);
        listview03 = (ListView) view.findViewById(R.id.listview03);

        btn03.setOnClickListener(this);
        View head=LayoutInflater.from(getContext()).inflate(R.layout.fragment03_head,null,false);
        listview03.addHeaderView(head);
        String[] data={"时间升序","时间降序"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,data);
        spinner03.setAdapter(adapter);

        car03s=new ArrayList<>();
        adapter03=new Adapter03(getContext(),R.layout.fragment03_list,car03s);
        listview03.setAdapter(adapter03);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        send();
    }

    private void send1() {
        car03s.clear();
        httpHelper.PostJosn("P3_1", "{\"type\":\"\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car03> car03=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car03>>(){}.getType());
                Collections.sort(car03, new Comparator<Car03>() {
                    @Override
                    public int compare(Car03 car03, Car03 t1) {
                        if (car03.getDt().compareTo(t1.getDt())>0){
                            return -1;
                        }else if (car03.getDt().compareTo(t1.getDt())==0){
                            return 0;
                        }else {
                            return 1;
                        }
                    }
                });
                car03s.addAll(car03);
                adapter03.notifyDataSetChanged();
            }
        });
    }
    private void send() {
        car03s.clear();
        httpHelper.PostJosn("P3_1", "{\"type\":\"\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car03> car03=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car03>>(){}.getType());
                Collections.sort(car03, new Comparator<Car03>() {
                    @Override
                    public int compare(Car03 car03, Car03 t1) {
                        if (car03.getDt().compareTo(t1.getDt())>0){
                            return 1;
                        }else if (car03.getDt().compareTo(t1.getDt())==0){
                            return 0;
                        }else {
                            return -1;
                        }
                    }
                });
                car03s.addAll(car03);
                adapter03.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn03:
                String a=spinner03.getSelectedItem().toString();
                if (a.equals("路口升序")){
                    send();
                }else {
                    send1();
                }

                break;
        }
    }
}
