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
public class Fragment37 extends Fragment implements View.OnClickListener {


    private EditText etext37_1;
    private EditText etext37_2;
    private EditText etext37_3;
    private Button btn37;

    public Fragment37() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment37, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        etext37_1 = (EditText) view.findViewById(R.id.etext37_1);
        etext37_2 = (EditText) view.findViewById(R.id.etext37_2);
        etext37_3 = (EditText) view.findViewById(R.id.etext37_3);
        btn37 = (Button) view.findViewById(R.id.btn37);

        btn37.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn37:
                if (TextUtils.isEmpty(etext37_1.getText().toString())){
                    Toast.makeText(getContext(), "请填入完整数据", Toast.LENGTH_SHORT).show();
                }else {
                    Fragment37_1 fragment37_1=new Fragment37_1();
                    Bundle bundle=new Bundle();
                    bundle.putString("values1","车辆编号="+etext37_1.getText().toString()+"，付费金额="+etext37_2.getText().toString()+"元");
                    fragment37_1.setArguments(bundle);

                    FragmentManager fragmentManager=getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container,fragment37_1).commit();

                }

                break;
        }
    }


}
