package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car12;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter12_2 extends ArrayAdapter<Car12.DetailsBean> {
    public Adapter12_2(@NonNull Context context, int resource, @NonNull List<Car12.DetailsBean> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car12.DetailsBean bean=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment12_list2,null,false);
            viewHolder=new ViewHolder();
            viewHolder.text1=view.findViewById(R.id.text12_list2_1);
            viewHolder.text2=view.findViewById(R.id.text12_list2_2);
            viewHolder.text3=view.findViewById(R.id.text12_list2_3);
            viewHolder.text4=view.findViewById(R.id.text12_list2_4);
            viewHolder.text5=view.findViewById(R.id.text12_list2_5);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.text1.setText(String.valueOf(bean.getTime()));
        viewHolder.text2.setText(String.valueOf(bean.getAddress()));
        viewHolder.text3.setText(String.valueOf(bean.getContent()));
        viewHolder.text4.setText("扣分："+bean.getScore()+"分");
        viewHolder.text5.setText("罚款："+bean.getMoney()+"元");
        return view;
    }

    class ViewHolder{
        TextView text1,text2,text3,text4,text5;
    }
}
