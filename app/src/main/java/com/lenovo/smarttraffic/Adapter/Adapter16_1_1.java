package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car16_1_1;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter16_1_1 extends ArrayAdapter<Car16_1_1> {
    public Adapter16_1_1(@NonNull Context context, int resource, @NonNull List<Car16_1_1> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car16_1_1 car16_1_1=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment16_1_item,null,false);
            viewHolder=new ViewHolder();
            viewHolder.id=view.findViewById(R.id.image16_1);
            viewHolder.name=view.findViewById(R.id.text16_1_6);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.id.setImageResource(car16_1_1.getId());
        viewHolder.name.setText(String.valueOf(car16_1_1.getName()));
        return view;
    }

    class ViewHolder{
        ImageView id;
        TextView name;
    }
}
