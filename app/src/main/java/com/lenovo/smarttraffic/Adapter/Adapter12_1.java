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

import com.lenovo.smarttraffic.Entity.Car12;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter12_1 extends ArrayAdapter<Car12> {
    public Adapter12_1(@NonNull Context context, int resource, @NonNull List<Car12> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car12 car12=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment12_list1,null,false);
            viewHolder=new ViewHolder();
            viewHolder.text1=view.findViewById(R.id.text12_list1);
            viewHolder.text2=view.findViewById(R.id.text12_list2);
            viewHolder.text3=view.findViewById(R.id.text12_list3);
            viewHolder.text4=view.findViewById(R.id.text12_list4);
            viewHolder.jian=view.findViewById(R.id.jianimage12);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(getItem(position));
                notifyDataSetChanged();

            }
        });
        viewHolder.text1.setText(String.valueOf(car12.getCarno()));
        viewHolder.text2.setText("未处理违章"+car12.getCount()+"次");
        viewHolder.text3.setText("扣"+car12.getScore()+"分");
        viewHolder.text4.setText("罚款"+car12.getMoney()+"元");
        return view;
    }

    class ViewHolder{
       ImageView jian;
       TextView text1,text2,text3,text4;
    }
}
