package com.lenovo.smarttraffic;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter11;
import com.lenovo.smarttraffic.Entity.Car11;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment11 extends Fragment implements View.OnClickListener {


    private Spinner spinner11;
    private Button btn11_1;
    private Button btn11_2;
    private ListView listview11;
    private List<Car11> car11s;
    private Adapter11 adapter11;
    private HttpHelper httpHelper;
    private Gson gson;

    public Fragment11() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment11, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        spinner11 = (Spinner) view.findViewById(R.id.spinner11);
        btn11_1 = (Button) view.findViewById(R.id.btn11_1);
        btn11_2 = (Button) view.findViewById(R.id.btn11_2);
        listview11 = (ListView) view.findViewById(R.id.listview11);

        btn11_1.setOnClickListener(this);
        btn11_2.setOnClickListener(this);
        View head=LayoutInflater.from(getContext()).inflate(R.layout.fragment11_head,null,false);
        listview11.addHeaderView(head);
        String[] data={"路口升序","路口降序","红灯升序","红灯降序"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,data);
        spinner11.setAdapter(adapter);
        car11s=new ArrayList<>();
        adapter11=new Adapter11(getContext(),R.layout.fragment11_list,car11s);
        listview11.setAdapter(adapter11);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
        send1();
    }
    private void send2() {
        car11s.clear();
        httpHelper.PostJosn("P11_1", "{\"sortid\":\"\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car11> car11=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car11>>(){}.getType());
                Collections.sort(car11, new Comparator<Car11>() {
                    @Override
                    public int compare(Car11 car11, Car11 t1) {
                        if (car11.getWay()>t1.getWay()){
                            return -1;
                        }else if (car11.getWay()==t1.getWay()){
                            return 0;
                        }else {
                            return 1;
                        }
                    }
                });
                car11s.addAll(car11);
                adapter11.notifyDataSetChanged();
            }
        });
    }

    private void send1() {
        car11s.clear();
        httpHelper.PostJosn("P11_1", "{\"sortid\":\"\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car11> car11=gson.fromJson(jsonObject.optJSONArray("serverinfo").toString(),new TypeToken<List<Car11>>(){}.getType());
                Collections.sort(car11, new Comparator<Car11>() {
                    @Override
                    public int compare(Car11 car11, Car11 t1) {
                        if (car11.getWay()>t1.getWay()){
                            return 1;
                        }else if (car11.getWay()==t1.getWay()){
                            return 0;
                        }else {
                            return -1;
                        }
                    }
                });
                car11s.addAll(car11);
                adapter11.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn11_1:
                String a=spinner11.getSelectedItem().toString();
                if (a=="路口升序"){
                    send1();
                }else if (a=="路口降序"){
                    send2();
                }

                break;
            case R.id.btn11_2:

                AlertDialog.Builder  builder=new AlertDialog.Builder(getContext());
                View in=LayoutInflater.from(getContext()).inflate(R.layout.fragment11_item,null,false);
                EditText addred=in.findViewById(R.id.addred11);
                EditText addyellow=in.findViewById(R.id.addyellow11);
                EditText addgreen=in.findViewById(R.id.addgreen11);
                builder.setPositiveButton("取消",null);
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (TextUtils.isEmpty(addred.getText().toString())||TextUtils.isEmpty(addgreen.getText().toString())||TextUtils.isEmpty(addyellow.getText().toString())){
                            Toast.makeText(getContext(), "请填写完整信息", Toast.LENGTH_SHORT).show();
                        }else {
                            JsonObject jsonObject=new JsonObject();
                            JsonArray jsonElements=new JsonArray();
                            for (Car11 car11:car11s){
                                if (car11.isSelected()){
                                    jsonObject.addProperty("way",car11.getWay());
                                }
                            }
                            jsonElements.add(jsonObject);
                            JsonObject object=new JsonObject();
                            object.add("ways",jsonElements);
                            httpHelper.PostJosn("P11_2", object.toString(), new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    String f=jsonObject.optJSONObject("serverinfo").optString("result");
                                    Toast.makeText(getContext(), f, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                builder.setView(in);
                builder.create().show();


                break;
        }
    }
}
