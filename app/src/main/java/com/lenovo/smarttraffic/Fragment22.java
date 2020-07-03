package com.lenovo.smarttraffic;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter22;
import com.lenovo.smarttraffic.Entity.Car22;
import com.lenovo.smarttraffic.Helper.HttpHelper;
import com.lenovo.smarttraffic.Helper.SQLHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment22 extends Fragment implements View.OnClickListener {


    private Button btn22_1;
    private Button btn22_2;
    private ListView listview22;
    private List<Car22> car22s;
    private Adapter22 adapter22;
    private HttpHelper httpHelper;
    private Gson gson;
    private SQLHelper sqlHelper;

    public Fragment22() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment22, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btn22_1 = (Button) view.findViewById(R.id.btn22_1);
        btn22_2 = (Button) view.findViewById(R.id.btn22_2);
        listview22 = (ListView) view.findViewById(R.id.listview22);

        car22s=new ArrayList<>();
        adapter22=new Adapter22(getContext(),R.layout.fragment22_list,car22s);
        listview22.setAdapter(adapter22);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
        btn22_1.setOnClickListener(this);
        btn22_2.setOnClickListener(this);

        send();
    }

    private void send() {
        httpHelper.PostJosn("P9_1", "{\"user\":\"\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car22> car22=gson.fromJson(jsonObject.optJSONObject("serverinfo").optJSONArray("list").toString(),new TypeToken<List<Car22>>(){}.getType());
                car22s.addAll(car22);
                adapter22.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn22_1:
                send1();

                break;
            case R.id.btn22_2:
                Fragment22_1 fragment22_1=new Fragment22_1();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,fragment22_1).commit();


                break;
        }
    }

    private void send1() {
        ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("请稍等。。。");
        progressDialog.show();
        progressDialog.dismiss();
        StringBuffer buffer=new StringBuffer();
        for (Car22 car22:car22s){
            if (car22.isSelected()){
                String b= String.valueOf(car22.getCarId());
                buffer.append(car22.getCarId()+"");
            }
        }
        if (TextUtils.isEmpty(buffer)){
            Toast.makeText(getContext(), "请选择批量充值车的编号", Toast.LENGTH_SHORT).show();
        }else {
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            View in=LayoutInflater.from(getContext()).inflate(R.layout.fragment22_item,null,false);
            TextView addchehao=in.findViewById(R.id.addchehao22);
            EditText addjine=in.findViewById(R.id.addjine22);
            addchehao.setText(buffer+"");
            builder.setPositiveButton("取消",null);
            builder.setNegativeButton("充值", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (TextUtils.isEmpty(addjine.getText().toString())){
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "请输入金额", Toast.LENGTH_SHORT).show();
                    }else if (Integer.valueOf(addjine.getText().toString())<0||Integer.valueOf(addjine.getText().toString())>999){
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "请输入正确金额", Toast.LENGTH_SHORT).show();
                    }else {
                        JsonObject jsonObject=new JsonObject();
                        JsonArray jsonElements=new JsonArray();
                        for (Car22 car22:car22s){
                            if (car22.isSelected()){
                                jsonObject.addProperty("carid",car22.getCarId());
                            }
                        }
                        jsonElements.add(jsonObject);
                        JsonObject object=new JsonObject();
                        object.addProperty("money",Integer.valueOf(addjine.getText().toString()));
                        object.add("caridList",jsonElements);
                        httpHelper.PostJosn("P9_2", object.toString(), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                String a=jsonObject.optJSONObject("serverinfo").optString("result");
                                Toast.makeText(getContext(), a, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
            builder.setView(in);
            builder.create().show();
        }
    }
}
