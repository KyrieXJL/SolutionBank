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

import com.lenovo.smarttraffic.Entity.Car04;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter04_1 extends ArrayAdapter<Car04> {
    public Adapter04_1(@NonNull Context context, int resource, @NonNull List<Car04> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View  view;
        ViewHolder viewHolder;
        Car04 car04=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment04_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.name=view.findViewById(R.id.text04);
            viewHolder.id=view.findViewById(R.id.image04);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(String.valueOf(car04.getName()));
        viewHolder.id.setImageResource(car04.getId());
        return view;
    }

    class ViewHolder{
        ImageView id;
        TextView name;
    }
}
