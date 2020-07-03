package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car02;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter02 extends ArrayAdapter<Car02> {
    public Adapter02(@NonNull Context context, int resource, @NonNull List<Car02> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car02 car02=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment02_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.way=view.findViewById(R.id.way02);
            viewHolder.red=view.findViewById(R.id.red02);
            viewHolder.yellow=view.findViewById(R.id.yellow02);
            viewHolder.green=view.findViewById(R.id.green02);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.green.setText(String.valueOf(car02.getGreen()));
        viewHolder.red.setText(String.valueOf(car02.getRed()));
        viewHolder.way.setText(String.valueOf(car02.getWay()));
        viewHolder.yellow.setText(String.valueOf(car02.getYellow()));
        return view;
    }

    class ViewHolder{
        TextView way,red,yellow,green;
    }
}
