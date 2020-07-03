package com.lenovo.smarttraffic;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter12_1;
import com.lenovo.smarttraffic.Adapter.Adapter12_2;
import com.lenovo.smarttraffic.Entity.Car12;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment12_1 extends Fragment {


    private ListView listview12_1;
    private ImageView addimage12;
    private List<Car12> car12s;
    private Adapter12_1 adapter12_1;
    private ListView listview12_2;
    private HttpHelper httpHelper;
    private String carno;

    private Gson gson;
    private Adapter12_2 adapter12_2;
    private List<Car12.DetailsBean> beans;

    public Fragment12_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment12_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listview12_1 = (ListView) view.findViewById(R.id.listview12_1);
        addimage12 = (ImageView) view.findViewById(R.id.addimage12);
        listview12_2 = (ListView) view.findViewById(R.id.listview12_2);
        Bundle bundle=getArguments();
        carno=bundle.getString("values");
        car12s=new ArrayList<>();
        adapter12_1=new Adapter12_1(getContext(),R.layout.fragment12_list1,car12s);
        listview12_1.setAdapter(adapter12_1);
        httpHelper=HttpHelper.getInstance(getContext());
        send();

        beans=new ArrayList<>();
        adapter12_2=new Adapter12_2(getContext(),R.layout.fragment12_list2,beans);
        listview12_2.setAdapter(adapter12_2);
        gson=new Gson();
        send1();

        addimage12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),PICActivity12.class);
                startActivity(intent);
            }
        });

    }

    private void send1() {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("carno",carno);
        httpHelper.PostJosn("T201734_1", jsonObject.toString(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car12.DetailsBean> bean=gson.fromJson(jsonObject.optJSONObject("serverinfo").optJSONArray("details").toString(),new TypeToken<List<Car12.DetailsBean>>(){}.getType());
                beans.addAll(bean);
                adapter12_2.notifyDataSetChanged();
            }
        });
    }

    private void send() {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("carno",carno);
        httpHelper.PostJosn("T201734_1", jsonObject.toString(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                int score=jsonObject.optJSONObject("serverinfo").optInt("score");
                int money=jsonObject.optJSONObject("serverinfo").optInt("money");
                int count=jsonObject.optJSONObject("serverinfo").optInt("count");
                Car12 car12=new Car12();
                car12.setScore(score);
                car12.setMoney(money);
                car12.setCount(count);
                car12.setCarno("鲁"+carno);
                car12s.add(car12);
                adapter12_1.notifyDataSetChanged();
            }
        });
    }
}
