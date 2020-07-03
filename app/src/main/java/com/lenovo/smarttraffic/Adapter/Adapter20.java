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

import com.lenovo.smarttraffic.Entity.Car20;
import com.lenovo.smarttraffic.Entity.Car20_1;
import com.lenovo.smarttraffic.R;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter20 extends ArrayAdapter<Car20_1> {
    public Adapter20(@NonNull Context context, int resource, @NonNull List<Car20_1> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car20_1 car20_1=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment20_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.id=view.findViewById(R.id.image20_list);
            viewHolder.name=view.findViewById(R.id.text20_list);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(String.valueOf(car20_1.getName()));
        viewHolder.id.setImageResource(car20_1.getId());
        return view;
    }

    class ViewHolder{
        TextView name;
        ImageView id;
    }
}
