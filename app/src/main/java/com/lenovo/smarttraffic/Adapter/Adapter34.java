package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car34;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter34 extends ArrayAdapter<Car34> {
    public Adapter34(@NonNull Context context, int resource, @NonNull List<Car34> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car34 car34=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment34_3_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.id=view.findViewById(R.id.carno34);
            viewHolder.card=view.findViewById(R.id.card34);
            viewHolder.moeny=view.findViewById(R.id.money34);
            viewHolder.date=view.findViewById(R.id.date34);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.id.setText(String.valueOf(car34.getId()));
        viewHolder.card.setText(String.valueOf(car34.getCard()));
        viewHolder.moeny.setText(String .valueOf(car34.getMoney()));
        viewHolder.date.setText(String.valueOf(car34.getDate()));
        return view;
    }

    class ViewHolder{
        TextView id,card,moeny,date;
    }
}
