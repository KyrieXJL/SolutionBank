package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.Entity.Car16_2;

import java.util.List;

public class Adapter16_2 extends ArrayAdapter<Car16_2> {
    public Adapter16_2(@NonNull Context context, int resource, @NonNull List<Car16_2> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car16_2 car16_2=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment16_2_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.text1=view.findViewById(R.id.text16_2list1);
            viewHolder.text2=view.findViewById(R.id.text16_2list2);
            viewHolder.text3=view.findViewById(R.id.text16_2list3);
            viewHolder.text4=view.findViewById(R.id.text16_2list4);
            viewHolder.text5=view.findViewById(R.id.text16_2list5);
            viewHolder.text6=view.findViewById(R.id.text16_2list6);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag ();
        }

        viewHolder.text1.setText(car16_2.getDate()+"\n"+car16_2.getWeek());
        viewHolder.text2.setText("充值人："+car16_2.getCashier());
        viewHolder.text3.setText("车牌号："+car16_2.getCarno());
        viewHolder.text4.setText("充值："+car16_2.getMoney());
        viewHolder.text5.setText("余额："+car16_2.getBalance());
        viewHolder.text6.setText(car16_2.getDate());

        return view;
    }

    class ViewHolder{
        TextView text1,text2,text3,text4,text5,text6;
    }
}
