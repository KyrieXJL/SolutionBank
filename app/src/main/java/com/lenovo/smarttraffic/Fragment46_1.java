package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter46_1;
import com.lenovo.smarttraffic.Entity.Car46_1;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment46_1 extends Fragment {


    private ListView listview46_1;
    private Adapter46_1 adapter46_1;
    private List<Car46_1> car46_1s;
    private HttpHelper httpHelper;
    private Gson gson;

    public Fragment46_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment46_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listview46_1 = (ListView) view.findViewById(R.id.listview46_1);
        car46_1s=new ArrayList<>();
        adapter46_1=new Adapter46_1(getContext(),R.layout.fragment46_list,car46_1s);
        listview46_1.setAdapter(adapter46_1);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
        send();
    }

    private void send() {
        httpHelper.PostJosn("P11_1", "{\"sortid\":\"\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car46_1> car46_1=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car46_1>>(){}.getType());
                car46_1s.addAll(car46_1);
                adapter46_1.notifyDataSetChanged();
            }
        });
    }
}
