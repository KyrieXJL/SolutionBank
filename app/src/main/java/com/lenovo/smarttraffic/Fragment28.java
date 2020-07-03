package com.lenovo.smarttraffic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.Adapter.Adapter28;
import com.lenovo.smarttraffic.Entity.Car28;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment28 extends Fragment {


    private ExpandableListView elistview28;
    private List<Car28> car28s;
    private Adapter28 adapter28;
    private HttpHelper httpHelper;
    private Gson gson;

    public Fragment28() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment28, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        elistview28 = (ExpandableListView) view.findViewById(R.id.elistview28);
        car28s=new ArrayList<>();
        adapter28=new Adapter28(getContext(),car28s);
        elistview28.setAdapter(adapter28);
        httpHelper=HttpHelper.getInstance(getContext());
        gson=new Gson();
        send();
    }

    private void send() {
        httpHelper.PostJosn("P10_1", "{\"cid\":0,\"sid\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Car28> car28=gson.fromJson(jsonObject.optJSONObject("serverinfo").optJSONArray("data").toString(),new TypeToken<List<Car28>>(){}.getType());
                for (Car28 car281:car28){
                    List<Car28.CarsBean> carsBeans=car281.getCars();
                    Collections.sort(carsBeans, new Comparator<Car28.CarsBean>() {
                        @Override
                        public int compare(Car28.CarsBean carsBean, Car28.CarsBean t1) {
                            if (carsBean.getDistance()>t1.getDistance()){
                                return 1;
                            }else if (carsBean.getDistance()==t1.getDistance()){
                                return 0;
                            }else {
                                return -1;
                            }
                        }
                    });
                }
                car28s.addAll(car28);
                adapter28.notifyDataSetChanged();
            }
        });
    }
}
