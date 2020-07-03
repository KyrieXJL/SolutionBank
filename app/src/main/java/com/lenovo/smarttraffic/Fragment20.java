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
import com.lenovo.smarttraffic.Adapter.Adapter20;
import com.lenovo.smarttraffic.Entity.Car20;
import com.lenovo.smarttraffic.Entity.Car20_1;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment20 extends Fragment {


    private TextView text20_1;
    private TextView text20_2;
    private TextView text20_3;
    private TextView text20_4;
    private TextView text20_5;
    private GridView gridview20;
    private HttpHelper httpHelper;
    private List<Car20_1> car20_1s;
    private Adapter20 adapter20;

    public Fragment20() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment20, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text20_1 = (TextView) view.findViewById(R.id.text20_1);
        text20_2 = (TextView) view.findViewById(R.id.text20_2);
        text20_3 = (TextView) view.findViewById(R.id.text20_3);
        text20_4 = (TextView) view.findViewById(R.id.text20_4);
        text20_5 = (TextView) view.findViewById(R.id.text20_5);
        gridview20 = (GridView) view.findViewById(R.id.gridview20);
        httpHelper=HttpHelper.getInstance(getContext());
        send();
    }

    private void send() {
        httpHelper.PostJosn("P16_2", "{\"userid\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String name=jsonObject.optJSONObject("serverinfo").optString("name");
                text20_1.setText("姓名："+name);
                String sex=jsonObject.optJSONObject("serverinfo").optString("sex");
                text20_2.setText("性别："+sex);
                String mobile=jsonObject.optJSONObject("serverinfo").optString("mobile");
                text20_3.setText("手机号："+mobile);
                String id=jsonObject.optJSONObject("serverinfo").optString("id");
                text20_4.setText("身份证号："+id);
                String date=jsonObject.optJSONObject("serverinfo").optString("registetime");
                text20_5.setText("注册时间："+date);

                Gson gson=new Gson();
                car20_1s=new ArrayList<>();
                adapter20=new Adapter20(getContext(),R.layout.fragment20_list,car20_1s);
                gridview20.setAdapter(adapter20);
                List<Car20.CarlistBean> carlistBeans=gson.fromJson(jsonObject.optJSONObject("serverinfo").optJSONArray("carlist").toString(),new TypeToken<List<Car20.CarlistBean>>(){}.getType());
                for (Car20.CarlistBean carlistBean:carlistBeans){
                    if (carlistBean.getBrand().equals("benchi")){
                        car20_1s.add(new Car20_1(R.mipmap.benchi,carlistBean.getCarno()));
                    }else if (carlistBean.getBrand().equals("baoma")){
                        car20_1s.add(new Car20_1(R.mipmap.baoma,carlistBean.getCarno()));
                    }

                }

                adapter20.notifyDataSetChanged();

            }
        });
    }
}
