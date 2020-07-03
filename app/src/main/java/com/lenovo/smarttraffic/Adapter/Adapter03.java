package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car03;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter03 extends ArrayAdapter<Car03> {
    public Adapter03(@NonNull Context context, int resource, @NonNull List<Car03> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car03 car03=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment03_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.user=view.findViewById(R.id.user03);
            viewHolder.carno=view.findViewById(R.id.carno03);
            viewHolder.carid=view.findViewById(R.id.carid03);
            viewHolder.money=view.findViewById(R.id.money03);
            viewHolder.date=view.findViewById(R.id.date03);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.date.setText(String.valueOf(car03.getDt()));
        viewHolder.user.setText(String.valueOf(car03.getUser()));
        viewHolder.money.setText(String.valueOf(car03.getMoney()));
        viewHolder.carno.setText(String.valueOf(car03.getNo()));
        viewHolder.carid.setText(String.valueOf(car03.getCarId()));
        return view;
    }

    class ViewHolder{
        TextView carno,carid,money,user,date;
    }
}
