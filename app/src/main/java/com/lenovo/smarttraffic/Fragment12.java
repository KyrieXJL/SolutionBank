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
public class Fragment12 extends Fragment implements View.OnClickListener {


    private EditText etext12;
    private Button btn12;

    public Fragment12() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment12, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        etext12 = (EditText) view.findViewById(R.id.etext12);
        btn12 = (Button) view.findViewById(R.id.btn12);

        btn12.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn12:
                if (TextUtils.isEmpty(etext12.getText().toString())){
                    Toast.makeText(getContext(), "请输入车牌号！", Toast.LENGTH_SHORT).show();
                }else if (etext12.getText().toString().equals("B10001")||etext12.getText().toString().equals("B10002")||etext12.getText().toString().equals("B10003")||etext12.getText().toString().equals("B10004")||etext12.getText().toString().equals("B10005")){
                    Fragment12_1 fragment12_1=new Fragment12_1();
                    Bundle bundle=new Bundle();
                    bundle.putString("values",etext12.getText().toString());
                    fragment12_1.setArguments(bundle);
                    FragmentManager fragmentManager=getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container,fragment12_1).commit();
                }else {
                    Toast.makeText(getContext(), "未查询到此车号", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

   
}
