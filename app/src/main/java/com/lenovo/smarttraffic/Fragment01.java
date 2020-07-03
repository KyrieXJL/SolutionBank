package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.JsonObject;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment01 extends Fragment implements View.OnClickListener {


    private TextView text01;
    private Spinner spinner01;
    private EditText etext01;
    private Button btn01_1;
    private Button btn01_2;
    private HttpHelper httpHelper;

    public Fragment01() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment01, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text01 = (TextView) view.findViewById(R.id.text01);
        spinner01 = (Spinner) view.findViewById(R.id.spinner01);
        etext01 = (EditText) view.findViewById(R.id.etext01);
        btn01_1 = (Button) view.findViewById(R.id.btn01_1);
        btn01_2 = (Button) view.findViewById(R.id.btn01_2);

        btn01_1.setOnClickListener(this);
        btn01_2.setOnClickListener(this);
        String[] data={"1","2","3"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,data);
        spinner01.setAdapter(adapter);
        httpHelper=HttpHelper.getInstance(getContext());
        send();
    }

    private void send() {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("carId",Integer.valueOf(spinner01.getSelectedItem().toString()));

        httpHelper.PostJosn("P1_1", jsonObject.toString(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                double a=jsonObject.optJSONObject("serverinfo").optDouble("balance");
                text01.setText(a+"");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01_1:
                send();

                break;
            case R.id.btn01_2:
                if (TextUtils.isEmpty(etext01.getText().toString())){
                    Toast.makeText(getContext(), "请输出金额", Toast.LENGTH_SHORT).show();

                }else if (Integer.valueOf(etext01.getText().toString())>999||Integer.valueOf(etext01.getText().toString())<0){
                    Toast.makeText(getContext(), "请输入正确金额", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "充值成功", Toast.LENGTH_SHORT).show();
                    text01.setText((Double.valueOf(text01.getText().toString())+Double.valueOf(etext01.getText().toString()))+"");
                }


                break;
        }
    }


}
