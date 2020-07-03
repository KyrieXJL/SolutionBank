package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car50;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter50_2 extends ArrayAdapter<Car50.DetailsBean> {
    public Adapter50_2(@NonNull Context context, int resource, @NonNull List<Car50.DetailsBean> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car50.DetailsBean bean=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment50_2_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1=view.findViewById(R.id.text50_list2_1);
            viewHolder.textView2=view.findViewById(R.id.text50_list2_2);
            viewHolder.textView3=view.findViewById(R.id.text50_list2_3);
            viewHolder.textView4=view.findViewById(R.id.text50_list2_4);
            viewHolder.textView5=view.findViewById(R.id.text50_list2_5);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.textView1.setText(String.valueOf(bean.getTime()));
        viewHolder.textView2.setText(String.valueOf(bean.getAddress()));
        viewHolder.textView3.setText(String.valueOf(bean.getContent()));
        viewHolder.textView4.setText("扣分："+bean.getScore()+"分");
        viewHolder.textView5.setText("罚款："+bean.getMoney()+"元");
        return view;
    }

    class ViewHolder{
        TextView textView1,textView2,textView3,textView4,textView5;
    }
}
