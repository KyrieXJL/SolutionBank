package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter16_2;
import com.lenovo.smarttraffic.Helper.HttpHelper;
import com.lenovo.smarttraffic.Entity.Car16_2;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment16_2 extends Fragment {


    private ListView listview16_2;
    private TextView text16_2;
    private List<Car16_2> car16_2s;
    private Adapter16_2 adapter16_2;
    private HttpHelper httpHelper;
    private Gson gson;
    private int count=0;

    public Fragment16_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment16_2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listview16_2 = (ListView) view.findViewById(R.id.listview16_2);
        text16_2 = (TextView) view.findViewById(R.id.text16_2);

        car16_2s=new ArrayList<>();
        adapter16_2=new Adapter16_2(getContext(),R.layout.fragment16_2_list,car16_2s);
        listview16_2.setAdapter(adapter16_2);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
        send();
    }

    private void send() {
        httpHelper.PostJosn("P16_1", "{\"userid\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car16_2> car16_2=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car16_2>>(){}.getType());
                for (Car16_2 car16_21:car16_2){
                    count=car16_21.getMoney();
                }
                text16_2.setText("总充值："+count+"元");
                car16_2s.addAll(car16_2);
                adapter16_2.notifyDataSetChanged();
            }
        });
    }
}
