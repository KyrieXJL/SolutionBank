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

import com.bumptech.glide.Glide;
import com.lenovo.smarttraffic.Entity.Car35;
import com.lenovo.smarttraffic.R;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter35 extends ArrayAdapter<Car35> {
    public Adapter35(@NonNull Context context, int resource, @NonNull List<Car35> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car35 car35=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fargment35_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.id=view.findViewById(R.id.image35);
            viewHolder.text1=view.findViewById(R.id.text35_1);
            viewHolder.text2=view.findViewById(R.id.text35_2);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        Glide.with(getContext()).load(car35.getPic()).into(viewHolder.id);
        viewHolder.text1.setText(String.valueOf(car35.getName()));
        viewHolder.text2.setText("票价￥"+String.valueOf(car35.getPrice()));
        return view;
    }

    class ViewHolder{
        ImageView id;
        TextView text1,text2;
    }
}
