package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car31;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter31 extends ArrayAdapter<Car31> {
    public Adapter31(@NonNull Context context, int resource, @NonNull List<Car31> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car31 car31=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment31_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.biaoti=view.findViewById(R.id.text31_list1);
            viewHolder.date=view.findViewById(R.id.text31_list2);
            viewHolder.zhuangtai=view.findViewById(R.id.text31_list3);
            viewHolder.neirong=view.findViewById(R.id.text31_list4);
            viewHolder.tel=view.findViewById(R.id.text31_list5);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.biaoti.setText("标题："+car31.getBiaoti());
        viewHolder.date.setText("提交时间："+car31.getDate());
        viewHolder.zhuangtai.setText("状态："+car31.getZhuangtai());
        viewHolder.neirong.setText("回复内容："+car31.getNeirong());
        viewHolder.tel.setText("联系方式："+car31.getTel());
        return view;
    }

    class ViewHolder{
        TextView biaoti,date,zhuangtai,neirong,tel;
    }
}
