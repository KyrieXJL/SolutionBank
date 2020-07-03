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

import com.lenovo.smarttraffic.Entity.Car50;
import com.lenovo.smarttraffic.R;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter50_1 extends ArrayAdapter<Car50> {
    public Adapter50_1(@NonNull Context context, int resource, @NonNull List<Car50> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car50 car50=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment50_1_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1=view.findViewById(R.id.text50_list1_1);
            viewHolder.textView2=view.findViewById(R.id.text50_list1_2);
            viewHolder.imageView=view.findViewById(R.id.image50_2);
            viewHolder.textView3=view.findViewById(R.id.text50_list1_3);
            viewHolder.textView4=view.findViewById(R.id.text50_list1_4);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.textView1.setText(String.valueOf(car50.getCarno()));
        viewHolder.textView2.setText("未处理违章"+String.valueOf(car50.getCount())+"次");
        viewHolder.textView3.setText("扣"+car50.getScore()+"分");
        viewHolder.textView4.setText("罚款"+car50.getMoney()+"元");
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(getItem(position));
            }
        });
        return view;
    }

    class ViewHolder{
        TextView textView1,textView2,textView3,textView4;
        ImageView imageView;
    }
}
