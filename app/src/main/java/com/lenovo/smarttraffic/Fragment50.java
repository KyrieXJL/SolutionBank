package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment50 extends Fragment implements View.OnClickListener {


    private EditText etext50;
    private Button btn50;

    public Fragment50() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment50, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        etext50 = (EditText) view.findViewById(R.id.etext50);
        btn50 = (Button) view.findViewById(R.id.btn50);

        btn50.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn50:
                if (TextUtils.isEmpty(etext50.getText().toString())){
                    Toast.makeText(getContext(), "请输入查询车号", Toast.LENGTH_SHORT).show();
                }else if (etext50.getText().toString().equals("B10001")||etext50.getText().toString().equals("B10002")||etext50.getText().toString().equals("B10003")||etext50.getText().toString().equals("B10004")||etext50.getText().toString().equals("B10005")){
                    Toast.makeText(getContext(), "查询成功", Toast.LENGTH_SHORT).show();
                    Fragment50_1 fragment50_1=new Fragment50_1();
                    Bundle bundle=new Bundle();
                    bundle.putString("chehao",etext50.getText().toString());
                    fragment50_1.setArguments(bundle);
                    FragmentManager fragmentManager=getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container,fragment50_1).commit();
                }else {
                    Toast.makeText(getContext(), "未查询到此车号", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    
}
