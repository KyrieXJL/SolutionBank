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
public class Fragment23 extends Fragment implements View.OnClickListener {


    private TextView text23;
    private EditText etxt23;
    private Button btn23;
    private SharedPreferences sharedPreferences;

    public Fragment23() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment23, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text23 = (TextView) view.findViewById(R.id.text23);
        etxt23 = (EditText) view.findViewById(R.id.etxt23);
        btn23 = (Button) view.findViewById(R.id.btn23);

        btn23.setOnClickListener(this);
        sharedPreferences=getContext().getSharedPreferences("Car23",Context.MODE_PRIVATE);
        int a=sharedPreferences.getInt("values",0);
        if (a==0){
            text23.setText("当前1~4号小车账户余额告警阈值未设置！");
        }else {
            text23.setText("我的1-4号车账户余额告警阈值值为"+a+"元");
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn23:
                if (TextUtils.isEmpty(etxt23.getText().toString())){
                    Toast.makeText(getContext(), "请输入金额", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), "设置成功", Toast.LENGTH_SHORT).show();
                    text23.setText("我的1-4号车账户余额告警阈值为"+Integer.valueOf(etxt23.getText().toString())+"元");
                    SharedPreferences.Editor editor=getContext().getSharedPreferences("Car23",Context.MODE_PRIVATE).edit();
                    editor.putInt("values",Integer.valueOf(etxt23.getText().toString()));
                    editor.commit();

                }

                break;
        }
    }


}
