package com.lenovo.smarttraffic;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lenovo.smarttraffic.Entity.Car35;
import com.lenovo.smarttraffic.Entity.Car35_2;
import com.lenovo.smarttraffic.Helper.HttpHelper;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment35_1 extends Fragment {



    private ImageView image35_1;
    private TextView text35_list_1;
    private RatingBar rbar35_1;
    private TextView text35_list_2;
    private HttpHelper httpHelper;

    public Fragment35_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment35_1, container, false);
        initView(view);
        send();
        return view;
    }

    private void initView(View view) {
        image35_1 = (ImageView) view.findViewById(R.id.image35_1);
        text35_list_1 = (TextView) view.findViewById(R.id.text35_list_1);
        rbar35_1 = (RatingBar) view.findViewById(R.id.rbar35_1);
        text35_list_2 = (TextView) view.findViewById(R.id.text35_list_2);

        httpHelper=new HttpHelper(getContext());

        text35_list_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+"010-88888888"));
               // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    private void send() {
        httpHelper.PostJosn("P35_2", "{\"id\":0}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                /*Gson gson=new Gson();
                Car35_2 car35_2=gson.fromJson(jsonObject.toString(),Car35_2.class);
                if (car35_2.getCode()==0){
                    int rate = car35_2.getServerinfo().getPlace().getRate();
                }*/

                String image = jsonObject.optJSONObject("serverinfo").optJSONObject("place").optString("image");
                String description = jsonObject.optJSONObject("serverinfo").optJSONObject("place").optString("description");
                int rate=jsonObject.optJSONObject("serverinfo").optJSONObject("place").optInt("rate");
                rbar35_1.setRating(rate);
                Glide.with(getContext()).load(image).into(image35_1);
                text35_list_1.setText(description);
                text35_list_2.setText("010-88888888");

            }
        });
    }
}
