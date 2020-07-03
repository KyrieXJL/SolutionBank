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

import com.lenovo.smarttraffic.Entity.Car30;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter30 extends ArrayAdapter<Car30> {
    public Adapter30(@NonNull Context context, int resource, @NonNull List<Car30> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car30 car30=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment30_lsit,null,false);
            viewHolder=new ViewHolder();
            viewHolder.name=view.findViewById(R.id.text30);
            viewHolder.imageView=view.findViewById(R.id.image30);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(car30.getId());
        viewHolder.name.setText(car30.getName());
        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView name;
    }
}
