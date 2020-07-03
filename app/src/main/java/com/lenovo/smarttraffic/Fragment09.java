package com.lenovo.smarttraffic;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
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
import com.lenovo.smarttraffic.Adapter.Adapter09;
import com.lenovo.smarttraffic.Entity.Car09;
import com.lenovo.smarttraffic.Entity.Car09_2;
import com.lenovo.smarttraffic.Helper.HttpHelper;
import com.lenovo.smarttraffic.Helper.SQLHelper;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment09 extends Fragment implements View.OnClickListener {


    private Button btn09_1;
    private Button btn09_2;
    private ListView listview09;
    private List<Car09> car09s;
    private Adapter09 adapter09;
    private HttpHelper httpHelper;
    private Gson gson;


    public Fragment09() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment09, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btn09_1 = (Button) view.findViewById(R.id.btn09_1);
        btn09_2 = (Button) view.findViewById(R.id.btn09_2);
        listview09 = (ListView) view.findViewById(R.id.listview09);

        btn09_1.setOnClickListener(this);
        btn09_2.setOnClickListener(this);
        car09s=new ArrayList<>();
        adapter09=new Adapter09(getContext(),R.layout.fragment09_list,car09s);
        listview09.setAdapter(adapter09);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
        send();
    }

    private void send() {
        httpHelper.PostJosn("P9_1", "{\"user\":\"\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car09> car09=gson.fromJson(jsonObject.optJSONObject("serverinfo").optJSONArray("list").toString(),new TypeToken<List<Car09>>(){}.getType());
                car09s.addAll(car09);
                adapter09.notifyDataSetChanged();
                /*Car09_2 car09_2=gson.fromJson(jsonObject.toString(),Car09_2.class);
                if (car09_2.getCode()==0){
                    List<Car09_2.ServerinfoBean.ListBean> list = car09_2.getServerinfo().getList();
                    car09s.addAll(list);
                }*/
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn09_1:
                ProgressDialog progressDialog=new ProgressDialog(getContext());
                progressDialog.setMessage("请稍等。。。");
                progressDialog.show();
                progressDialog.dismiss();
                StringBuffer buffer=new StringBuffer();
                for (Car09 car09:car09s){
                    if (car09.isSelected()){
                        String a=car09.getCard();
                        buffer.append(a);
                    }
                }
                if (TextUtils.isEmpty(buffer)){
                    Toast.makeText(getContext(), "请选择充值的车号", Toast.LENGTH_SHORT).show();
                }else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                    View in=LayoutInflater.from(getContext()).inflate(R.layout.fragment09_item,null,false);
                    TextView addchehao=in.findViewById(R.id.addchehao09);
                    EditText addmoney=in.findViewById(R.id.addmoney09);
                    addchehao.setText(buffer+"");
                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progressDialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("充值", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (TextUtils.isEmpty(addmoney.getText().toString())){
                                progressDialog.dismiss();
                                Toast.makeText(getContext(), "请输入金额", Toast.LENGTH_SHORT).show();
                            }else if (Integer.valueOf(addmoney.getText().toString())<0||Integer.valueOf(addmoney.getText().toString())>999){
                                Toast.makeText(getContext(), "请输入正确金额", Toast.LENGTH_SHORT).show();
                                progressDialog.show();
                            }else {
                                progressDialog.dismiss();

                                //card text,money integer,balance integer,user text,date text




                                JsonObject jsonObject=new JsonObject();
                                JsonArray jsonElements=new JsonArray();
                                for (Car09 car09:car09s){
                                    if (car09.isSelected()){
                                        SQLHelper sqlHelper=new SQLHelper(getContext(),"Car0921.db",null,1);
                                        SQLiteDatabase db=sqlHelper.getWritableDatabase();
                                        ContentValues values=new ContentValues();

                                        Date date=new Date();
                                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        String dt=simpleDateFormat.format(date);

                                        values.put("card",car09.getCard());
                                        values.put("balance",Integer.valueOf(addmoney.getText().toString())+Integer.valueOf(car09.getBalance()));
                                        values.put("money",Integer.valueOf(addmoney.getText().toString()));
                                        values.put("user",car09.getUser());
                                        values.put("date",dt);

                                        db.insert("car092",null,values);
                                        sqlHelper.close();



                                        jsonObject.addProperty("carid",car09.getCarId());
                                    }
                                }

                                jsonElements.add(jsonObject);
                                JsonObject object=new JsonObject();
                                object.addProperty("money",Integer.valueOf(addmoney.getText().toString()));
                                object.add("caridList",jsonElements);



                                httpHelper.PostJosn("P9_2", object.toString(), new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {
                                        String  a=jsonObject.optJSONObject("serverinfo").optString("result");
                                        Toast.makeText(getContext(), a, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }


                        }
                    });
                    builder.setView(in);
                    builder.create().show();
                }



                break;
            case R.id.btn09_2:
                Fragment09_1 fragment09_1=new Fragment09_1();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,fragment09_1).commit();

                break;
        }
    }
}
