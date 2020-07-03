package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car21;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter21 extends ArrayAdapter<Car21> {
    public Adapter21(@NonNull Context context, int resource, @NonNull List<Car21> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car21 car21=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment21_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.yellow=view.findViewById(R.id.yellow21);
            viewHolder.way=view.findViewById(R.id.way21);
            viewHolder.green=view.findViewById(R.id.green21);
            viewHolder.red=view.findViewById(R.id.red21);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.red.setText(String.valueOf(car21.getRed()));
        viewHolder.way.setText(String.valueOf(car21.getWay()));
        viewHolder.green.setText(String.valueOf(car21.getGreen()));
        viewHolder.yellow.setText(String.valueOf(car21.getYellow()));
        return view;
    }

    class  ViewHolder{
        TextView way,red,yellow,green;
    }
}
