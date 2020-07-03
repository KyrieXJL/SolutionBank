package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.Entity.Car31;
import com.lenovo.smarttraffic.Entity.Car46_1;
import com.lenovo.smarttraffic.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Adapter46_1 extends ArrayAdapter<Car46_1> {
    private Timer timer;
    public Adapter46_1(@NonNull Context context, int resource, @NonNull List<Car46_1> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car46_1 car46_1=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment46_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1=view.findViewById(R.id.text46_list1);
            viewHolder.textView2=view.findViewById(R.id.text46_list2);
            viewHolder.textView3=view.findViewById(R.id.text46_list3);
            viewHolder.textView4=view.findViewById(R.id.text46_list4);
            viewHolder.lktext1=view.findViewById(R.id.lktext46_1);
            viewHolder.lktext2=view.findViewById(R.id.lktext46_2);
            viewHolder.lktext3=view.findViewById(R.id.lktext46_3);
            viewHolder.lktext4=view.findViewById(R.id.lktext46_4);
            viewHolder.button1=view.findViewById(R.id.lkbtn46_1);
            viewHolder.button2=view.findViewById(R.id.lkbtn46_2);
            viewHolder.linearLayout_zhu=view.findViewById(R.id.linear46_zhu);
            viewHolder.linearLayout1=view.findViewById(R.id.lklinear46_1);
            viewHolder.linearLayout2=view.findViewById(R.id.lklinear46_2);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.linearLayout_zhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "555555", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "666666666", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.textView1.setText("路口"+car46_1.getWay());
        viewHolder.textView2.setText("绿灯"+car46_1.getGreen()+"秒");
        viewHolder.textView3.setText("黄灯"+car46_1.getYellow()+"秒");
        viewHolder.textView4.setText("红灯"+car46_1.getRed()+"秒");
        viewHolder.lktext1.setText("绿灯"+car46_1.getGreen()+"秒");
        viewHolder.lktext2.setText(car46_1.getGreen()+"");
        viewHolder.linearLayout1.setBackgroundResource(R.drawable.red_bg_03);//动态设置改变背景图片
      // timer=new Timer();
       /*new Thread(new Runnable() {
           @Override
           public void run() {
               timer=new Timer();
               timer.scheduleAtFixedRate(new TimerTask() {
                   @Override
                   public void run() {
                       int a=Integer.valueOf(car46_1.getGreen());
                       a--;
                       viewHolder.lktext2.setText(a+"");
                   }
               },0,1000);
           }
       }).start();;*/
       /* timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {


            }
        },0,1000);*/

        return view;
    }

    class ViewHolder{
        TextView textView1,textView2,textView3,textView4,lktext1,lktext2,lktext3,lktext4;
        LinearLayout linearLayout_zhu,linearLayout1,linearLayout2;
        Button button1,button2;

    }



}
