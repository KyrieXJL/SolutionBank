package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter35;
import com.lenovo.smarttraffic.Entity.Car35;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment35 extends Fragment {


    private GridView gridview35;
    private HttpHelper httpHelper;
    private Gson gson;
    private List<Car35> car35s;
    private Adapter35 adapter35;

    public Fragment35() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment35, container, false);
        initView(view);
        gridview35.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Fragment35_1 fragment35_1=new Fragment35_1();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,fragment35_1).commit();
            }
        });
        return view;
    }

    private void initView(View view) {
        gridview35 = (GridView) view.findViewById(R.id.gridview35);
        car35s=new ArrayList<>();
        adapter35=new Adapter35(getContext(),R.layout.fargment35_list,car35s);
        gridview35.setAdapter(adapter35);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
        send();
    }

    private void send() {
        httpHelper.PostJosn("P35_1_1", "{}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car35> car35=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car35>>(){}.getType());
                car35s.addAll(car35);
                adapter35.notifyDataSetChanged();
            }
        });
    }
}
