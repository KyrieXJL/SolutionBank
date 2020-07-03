package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car22_1;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter22_1 extends ArrayAdapter<Car22_1> {
    public Adapter22_1(@NonNull Context context, int resource, @NonNull List<Car22_1> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car22_1 car22_1=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment22_1_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.date1=view.findViewById(R.id.text22_1list_1);
            viewHolder.user=view.findViewById(R.id.text22_1list_2);
            viewHolder.carid=view.findViewById(R.id.text22_1list_3);
            viewHolder.money=view.findViewById(R.id.text22_1list_4);
            viewHolder.balance=view.findViewById(R.id.text22_1list_5);
            viewHolder.date2=view.findViewById(R.id.text22_1list_6);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.date1.setText(String.valueOf(car22_1.getDate()));
        viewHolder.user.setText("充值人："+String.valueOf(car22_1.getUser()));
        viewHolder.carid.setText("车牌编号："+String.valueOf(car22_1.getCarid()));
        viewHolder.money.setText("充值："+String.valueOf(car22_1.getMoney()));
        viewHolder.balance.setText("余额："+String.valueOf(car22_1.getBalance()));
        viewHolder.date2.setText(String.valueOf(car22_1.getDate()));
        return view;

    }

    class ViewHolder{
        TextView date1,user,carid,money,balance,date2;
    }
}
