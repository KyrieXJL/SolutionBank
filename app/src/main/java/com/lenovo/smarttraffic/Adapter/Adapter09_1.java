package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car09;
import com.lenovo.smarttraffic.Entity.Car09_1;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter09_1 extends ArrayAdapter<Car09_1> {
    public Adapter09_1(@NonNull Context context, int resource, @NonNull List<Car09_1> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view;
        ViewHolder viewHolder;
        Car09_1 car09_1=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment09_1_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.balance=view.findViewById(R.id.text09_list_5);
            viewHolder.date=view.findViewById(R.id.text09_list_1);
            viewHolder.user=view.findViewById(R.id.text09_list_2);
            viewHolder.card=view.findViewById(R.id.text09_list_3);
            viewHolder.money=view.findViewById(R.id.text09_list_4);
            viewHolder.date2=view.findViewById(R.id.text09_list_6);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.date.setText(String.valueOf(car09_1.getDate1()));
        viewHolder.user.setText("充值人："+car09_1.getUser());
        viewHolder.card.setText("车牌号："+car09_1.getCard());
        viewHolder.money.setText("充值："+car09_1.getMoney());
        viewHolder.balance.setText("余额："+car09_1.getBalance());
        viewHolder.date2.setText(String.valueOf(car09_1.getDate2()));

        return view;


    }

    class ViewHolder{
        TextView date,user,card,money,balance,date2;
    }
}
