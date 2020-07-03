package com.lenovo.smarttraffic;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment16_3 extends Fragment implements View.OnClickListener {


    private EditText etext16_3;
    private Button btn16_3;
    private SharedPreferences sharedPreferences;
    private TextView text16_3;

    public Fragment16_3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment16_3, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        etext16_3 = (EditText) view.findViewById(R.id.etext16_3);
        btn16_3 = (Button) view.findViewById(R.id.btn16_3);
        text16_3 = (TextView) view.findViewById(R.id.text16_3);

        btn16_3.setOnClickListener(this);
        sharedPreferences = getContext().getSharedPreferences("car16", Context.MODE_PRIVATE);
        int a = sharedPreferences.getInt("money", 0);
        if (a == 0) {
            text16_3.setText("当前1~4号小车账户余额告警阈值未设置！");

        }else {
            text16_3.setText("当前1~4号小车账户余额告警阈值："+a+"元");
        }




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn16_3:
                if (TextUtils.isEmpty(etext16_3.getText().toString())) {
                    Toast.makeText(getContext(), "请输入阈值", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "设置成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("car16", Context.MODE_PRIVATE).edit();
                    editor.putInt("money", Integer.valueOf(etext16_3.getText().toString()));
                    text16_3.setText("当前1~4号小车账户余额告警阈值："+etext16_3.getText().toString()+"元");
                    editor.commit();
                }

                break;
        }
    }


}
