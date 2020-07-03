package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment05_1 extends Fragment implements View.OnClickListener {


    private TextView text05;
    private Button btn05;

    public Fragment05_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment05_1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text05 = (TextView) view.findViewById(R.id.text05);
        btn05 = (Button) view.findViewById(R.id.btn05);

        btn05.setOnClickListener(this);
        Bundle bundle=getArguments();
        int data=bundle.getInt("value");
        text05.setText(data+"");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn05:
                Fragment05 fragment05=new Fragment05();
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,fragment05).commit();

                break;
        }
    }
}
