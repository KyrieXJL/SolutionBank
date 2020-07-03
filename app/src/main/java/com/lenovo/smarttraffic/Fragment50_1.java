package com.lenovo.smarttraffic;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter50_1;
import com.lenovo.smarttraffic.Adapter.Adapter50_2;
import com.lenovo.smarttraffic.Entity.Car50;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment50_1 extends Fragment {


    private ImageView image50_1;
    private ListView listview50_1;
    private ListView listview50_2;
    private List<Car50> car50s;
    private List<Car50.DetailsBean> beans;
    private Adapter50_1 adapter50_1;
    private Adapter50_2 adapter50_2;
    private HttpHelper httpHelper;
    private Gson gson;
    private String carNo;
    private TreeSet<String> treeSet=new TreeSet<String>();//用来装查询的车号



    public Fragment50_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment50_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        image50_1 = (ImageView) view.findViewById(R.id.image50_1);
        listview50_1 = (ListView) view.findViewById(R.id.listview50_1);
        listview50_2 = (ListView) view.findViewById(R.id.listview50_2);
        car50s=new ArrayList<>();
        adapter50_1=new Adapter50_1(getContext(),R.layout.fragment50_1_list,car50s);
        listview50_1.setAdapter(adapter50_1);
        beans=new ArrayList<>();
        adapter50_2=new Adapter50_2(getContext(),R.layout.fragment50_2_list,beans);
        listview50_2.setAdapter(adapter50_2);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
        Bundle bundle=getArguments();
        carNo=bundle.getString("chehao");
        send(carNo);
        listview50_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Car50 car50=car50s.get(i);
                beans.clear();
                for (Car50.DetailsBean bean:car50.getDetails()){
                    beans.add(bean);
                }
                adapter50_2.notifyDataSetChanged();
            }
        });
        listview50_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(),PICActivity12.class);
                startActivity(intent);
            }
        });
        image50_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                treeSet.add(carNo);//用来装查询的车号
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View  in=LayoutInflater.from(getContext()).inflate(R.layout.fragment50_item,null,false);
                EditText addchehao=in.findViewById(R.id.addchehao50);

                builder.setPositiveButton("返回",null);
                builder.setNegativeButton("查找", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int count=0;
                        TextView ceshi=in.findViewById(R.id.cheshi50);



                        for (String string:treeSet){
                            //ceshi.setText(string);
                            if (TextUtils.isEmpty(addchehao.getText().toString())){
                                Toast.makeText(getContext(), "请输入车号", Toast.LENGTH_SHORT).show();
                                Log.d("data",string);
                            }else {
                                if (addchehao.getText().toString().equals(string)){
                                    count++;
                                }

                            }

                        }
                        if (count>0){
                            Toast.makeText(getContext(), "查找重复", Toast.LENGTH_SHORT).show();
                        }else {
                            if (addchehao.getText().toString().equals("B10001")||addchehao.getText().toString().equals("B10002")||addchehao.getText().toString().equals("B10003")||addchehao.getText().toString().equals("B10004")||addchehao.getText().toString().equals("B10005")){
                                Toast.makeText(getContext(), "查找成功", Toast.LENGTH_SHORT).show();
                                treeSet.add(addchehao.getText().toString());
                                send(addchehao.getText().toString());
                            }else {
                                Toast.makeText(getContext(), "未查询到此车号", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                builder.setView(in);
                builder.create().show();
            }
        });
    }

    private void send(String carNo) {
        beans.clear();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("carno",carNo);
        httpHelper.PostJosn("T201734_1", jsonObject.toString(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Car50 car50=gson.fromJson(jsonObject.optJSONObject("serverinfo").toString(),new TypeToken<Car50>(){}.getType());
                car50s.add(car50);
                adapter50_1.notifyDataSetChanged();
                /*for (Car50 car501:car50s){
                    List<Car50.DetailsBean> bean=car501.getDetails();
                    beans.addAll(bean);
                }*/
                for (Car50.DetailsBean bean:car50.getDetails()){
                    beans.add(bean);
                }
                adapter50_2.notifyDataSetChanged();

            }
        });
    }
}
