package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car10_item;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter10_item extends ArrayAdapter<Car10_item> {
    public Adapter10_item(@NonNull Context context, int resource, @NonNull List<Car10_item> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car10_item car10_item=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment10_item_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1=view.findViewById(R.id.text10_item_list1);
            viewHolder.textView2=view.findViewById(R.id.text10_item_list2);
            viewHolder.textView3=view.findViewById(R.id.text10_item_list3);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.textView1.setText(String.valueOf(car10_item.getCid()));
        viewHolder.textView2.setText(String.valueOf(car10_item.getCid()));
        viewHolder.textView3.setText(String.valueOf(car10_item.getPersons()));
        return view;

    }

    class ViewHolder{
        TextView textView1,textView2,textView3;
    }
}
