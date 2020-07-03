package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter16_1_1;
import com.lenovo.smarttraffic.Entity.Car16_1;
import com.lenovo.smarttraffic.Entity.Car16_1_1;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment16_1 extends Fragment {


    private TextView text16_1_1;
    private TextView text16_1_2;
    private TextView text16_1_3;
    private TextView text16_1_4;
    private TextView text16_1_5;
    private GridView gridview16_1;
    private HttpHelper httpHelper;
    private Gson gson;
    private List<Car16_1_1> car16_1_1s;
    private Adapter16_1_1 adapter16_1_1;

    public Fragment16_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment16_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text16_1_1 = (TextView) view.findViewById(R.id.text16_1_1);
        text16_1_2 = (TextView) view.findViewById(R.id.text16_1_2);
        text16_1_3 = (TextView) view.findViewById(R.id.text16_1_3);
        text16_1_4 = (TextView) view.findViewById(R.id.text16_1_4);
        text16_1_5 = (TextView) view.findViewById(R.id.text16_1_5);
        gridview16_1 = (GridView) view.findViewById(R.id.gridview16_1);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
        car16_1_1s=new ArrayList<>();
        adapter16_1_1=new Adapter16_1_1(getContext(),R.layout.fragment16_1,car16_1_1s);
        gridview16_1.setAdapter(adapter16_1_1);
        send();

    }

    private void send() {
        httpHelper.PostJosn("P16_2", "{\"userid\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String name=jsonObject.optJSONObject("serverinfo").optString("name");
                text16_1_1.setText("姓名："+name);
                String sex=jsonObject.optJSONObject("serverinfo").optString("sex");
                text16_1_2.setText("性别："+sex);
                String mobile=jsonObject.optJSONObject("serverinfo").optString("mobile");
                text16_1_3.setText("手机号码："+mobile);
                String registetime=jsonObject.optJSONObject("serverinfo").optString("registetime");
                text16_1_5.setText("驻车时间："+registetime);
                String id=jsonObject.optJSONObject("serverinfo").optString("id");
                text16_1_4.setText("身份证号："+id);
                List<Car16_1.CarlistBean> carlistBeans=gson.fromJson(jsonObject.optJSONObject("serverinfo").optJSONArray("carlist").toString(),new TypeToken<List<Car16_1.CarlistBean>>(){}.getType());
                for (Car16_1.CarlistBean car16_1:carlistBeans){
                    String a=car16_1.getCarno();
                    String b=car16_1.getBrand();
                    if (b.equals("baoma")){
                        car16_1_1s.add(new Car16_1_1(R.mipmap.baoma,a));
                    }else if (b.equals("benchi")){
                        car16_1_1s.add(new Car16_1_1(R.mipmap.benchi,a));
                    }

                //    car16_1_1s.add(new Car16_1_1(R.mipmap.baoma,"宝马"));


                }

                adapter16_1_1.notifyDataSetChanged();
            }
        });
    }
}
